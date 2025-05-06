package com.br.equaly.auth.util;

import com.br.equaly.auth.application.port.input.CredentialUseCase;
import com.br.equaly.auth.application.port.output.JwtPort;
import com.br.equaly.auth.domain.enums.CredentialType;
import com.br.equaly.auth.domain.enums.Role;
import com.br.equaly.auth.domain.model.Credential;
import com.br.equaly.auth.domain.model.User;
import com.br.equaly.auth.infrastructure.adapter.out.repository.SessionTokenRepository;
import com.br.equaly.auth.infrastructure.entity.SessionTokenEntity;
import com.br.equaly.auth.model.OAuthTokenResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;

@Component
public class JwtUtils implements JwtPort {

    private final SessionTokenRepository sessionTokenRepository;
    private final CredentialUseCase credentialUseCase;

    public JwtUtils(SessionTokenRepository sessionTokenRepository, CredentialUseCase credentialUseCase) {
        this.sessionTokenRepository = sessionTokenRepository;
        this.credentialUseCase = credentialUseCase;
    }

    public OAuthTokenResponse encodeToken(Boolean isAdmin, User user){
        String appkey = getCredentialValue(isAdmin, user, CredentialType.ADMINISTRATIVE, CredentialType.OPERATIONAL);
        String secret = getCredentialSecret(isAdmin, user, CredentialType.ADMINISTRATIVE, CredentialType.OPERATIONAL);

        String tokenId = UUID.randomUUID().toString();
        Instant expiration = Instant.now().plusSeconds(ConstantsUtils.SESSION_EXPIRATION);
        Map<String, Object> claims = this.getClaimsFromToken(user, appkey, isAdmin);

        String token = Jwts.builder()
                .id(tokenId)
                .header()
                .add("typ", "JWT")
                .add("alg", "HS256")
                .and()
                .issuer("eQualy Authenticator v2")
                .subject(user.getId().toString())
                .claims(claims)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(expiration))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)))
                .compact();

        OAuthTokenResponse tokenResponse = new OAuthTokenResponse()
                .tokenType(ConstantsUtils.TOKEN_PREFIX)
                .accessToken(token)
                .refreshToken(Base64.getEncoder().encodeToString(tokenId.getBytes(StandardCharsets.UTF_8)))
                .expiresIn((int) (expiration.getEpochSecond() - Instant.now().getEpochSecond()));

        this.saveToken(tokenId, tokenResponse);
        return tokenResponse;
    }

    @Override
    public void saveToken(String id, OAuthTokenResponse oauthToken) {
        sessionTokenRepository.save(new SessionTokenEntity(
                id,
                DigestUtils.sha256Hex(oauthToken.getAccessToken().getBytes(StandardCharsets.UTF_8))
        ));
    }

    private String getCredentialValue(Boolean isAdmin, User user, CredentialType adminType, CredentialType operationalType) {
        CredentialType credentialType = isAdmin ? adminType : operationalType;
        return user.getCompany().getCredentials().stream()
                .filter(credential -> credential.getType().equals(credentialType))
                .findFirst()
                .map(Credential::getValue)
                .orElseThrow(() -> new IllegalArgumentException("Credencial não encontrada para o tipo: " + credentialType));
    }

    private String getCredentialSecret(Boolean isAdmin, User user, CredentialType adminType, CredentialType operationalType) {
        CredentialType credentialType = isAdmin ? adminType : operationalType;
        return user.getCompany().getCredentials().stream()
                .filter(credential -> credential.getType().equals(credentialType))
                .findFirst()
                .map(Credential::getSecret)
                .orElseThrow(() -> new IllegalArgumentException("Secret não encontrado para o tipo de credencial: " + credentialType));
    }

    public Map<String, Object> getClaimsFromToken(User user, String appkey, Boolean isAdmin) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("user", Map.of("username", user.getUsername(),
                "name", user.getUniversalUser().getName(),
                "identity_number", user.getUniversalUser().getDocumentNumber(),
                "preferred_username", user.getNickname(),
                "picture", user.getAvatarId()));

        claims.put("company", Map.of("company_id", user.getCompany().getId().toString(),
                "company_name", user.getCompany().getName(),
                "company_display_name", user.getCompany().getDisplayName(),
                "company_business_name", user.getCompany().getTradingName(),
                "company_tax_id", user.getCompany().getDocument(),
                "company_alias", user.getCompany().getAlias()));

        claims.put("department", Map.of("department_id", user.getDepartment().getId().toString(),
                "department_name", user.getDepartment().getName()));

        if (isAdmin) {
            claims.put("roles", user.getRoles().stream()
                    .filter(userRole ->
                            userRole.getName().equals(Role.EQUALY_MASTER_ADMIN) ||
                                    userRole.getName().equals(Role.MASTER_ADMIN) ||
                                    userRole.getName().equals(Role.COMMON_ADMIN))
                    .map(userRole -> userRole.getName().toString())
                    .toList());
        } else {
            claims.put("roles", user.getRoles().stream()
                    .filter(userRole ->
                            !userRole.getName().equals(Role.EQUALY_MASTER_ADMIN) &&
                                    !userRole.getName().equals(Role.MASTER_ADMIN) &&
                                    !userRole.getName().equals(Role.COMMON_ADMIN))
                    .map(userRole -> userRole.getName().toString())
                    .toList());
        }

        claims.put("azp", appkey);

        return claims;
    }

    public Jwt validateToken(String token, String applicationKey) {
        try{
            Credential credential = credentialUseCase.getCredentialByAppkey(applicationKey);
            io.jsonwebtoken.Jwt jwt = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(credential.getSecret())))
                    .build()
                    .parseSignedClaims(token);

            Claims claims = (Claims) jwt.getPayload();
            Optional<SessionTokenEntity> sessionToken = sessionTokenRepository.findById(claims.getId());

            if(!credential.getValue().equals(claims.get("azp"))
                    || sessionToken.isEmpty()
                    || !DigestUtils.sha256Hex(token.getBytes(StandardCharsets.UTF_8)).equals(sessionToken.get().getTokenHash())){
                return null;
            }

            Jwt.Builder jwtBuilder = Jwt.withTokenValue(token)
                    .header("alg", "HS256");
            claims.forEach((key, value) -> {
                if (value instanceof Long) {
                    if (key.equals("iat") || key.equals("exp") || key.equals("nbf")) {
                        jwtBuilder.claim(key, Instant.ofEpochSecond((Long) value));
                    } else {
                        jwtBuilder.claim(key, value);
                    }
                } else {
                    jwtBuilder.claim(key, value);
                }
            });

            return jwtBuilder.build();
        }catch(Exception e){
            return null;
        }
    }
}