package com.br.equaly.auth.application.port.output;

import com.br.equaly.auth.domain.model.User;
import com.br.equaly.auth.model.OAuthTokenResponse;
import org.springframework.security.oauth2.jwt.Jwt;

public interface JwtPort {
    OAuthTokenResponse encodeToken(Boolean isAdmin, User user);
    void saveToken(String id, OAuthTokenResponse token);
    Jwt validateToken(String token, String applicationKey);
}