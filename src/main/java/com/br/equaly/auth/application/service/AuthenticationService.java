package com.br.equaly.auth.application.service;

import com.br.equaly.auth.application.port.input.AuthenticationUseCase;
import com.br.equaly.auth.application.port.input.CompanyUseCase;
import com.br.equaly.auth.application.port.input.UserUseCase;
import com.br.equaly.auth.application.port.output.JwtPort;
import com.br.equaly.auth.domain.enums.CredentialType;
import com.br.equaly.auth.domain.enums.Role;
import com.br.equaly.auth.domain.model.User;
import com.br.equaly.auth.infrastructure.exception.BusinessException;
import com.br.equaly.auth.infrastructure.mapper.UserMapper;
import com.br.equaly.auth.model.OAuthTokenResponse;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AuthenticationService implements AuthenticationUseCase {

    private UserUseCase userUseCase;

    private JwtPort jwtPort;

    private CompanyUseCase companyUseCase;

    private MessageSource messageSource;

    private UserMapper userMapper;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthenticationService(UserUseCase userUseCase,CompanyUseCase companyUseCase,UserMapper userMapper, JwtPort jwtPort, MessageSource messageSource) {
        this.userUseCase = userUseCase;
        this.companyUseCase = companyUseCase;
        this.messageSource = messageSource;
        this.userMapper = userMapper;
        this.jwtPort = jwtPort;
    }

    @Override
    public OAuthTokenResponse authenticateUser(String password, Boolean isAdmin, Integer companyId, String login, String email, String document) {
        User user = userUseCase.getUser(companyId,login,email,document);
        this.validateAuthentication(user, password, isAdmin, Boolean.FALSE);
        return jwtPort.encodeToken(isAdmin, user);
    }

    public void validateAuthentication(User user, String password, Boolean isAdmin, Boolean refreshSession){

        if(refreshSession){
            if(user == null || !user.getIsActive() || user.getRoles().isEmpty() || user.getLastLogin() == null){
                throw new BusinessException(messageSource.getMessage("error.user_not_active", null, LocaleContextHolder.getLocale()));
            }
        }else{
            if(user == null || !user.getIsActive() || user.getRoles().isEmpty() || user.getLastLogin() == null || !passwordEncoder.matches(password, user.getPassword())){
                throw new BusinessException(messageSource.getMessage("error.user_not_active", null, LocaleContextHolder.getLocale()));
            }
        }

        if(!user.getCompany().getIsActive() || !user.getDepartment().getIsActive()){
            throw new BusinessException(messageSource.getMessage("error.company_not_active", null, LocaleContextHolder.getLocale()));
        }

        if(
                (isAdmin &&
                        (user.getRoles().stream().noneMatch(
                                role -> role.getName().equals(Role.EQUALY_MASTER_ADMIN)
                                        || role.getName().equals(Role.MASTER_ADMIN)
                                        || role.getName().equals(Role.COMMON_ADMIN)
                        )
                                || user.getCompany().getCredentials().stream().noneMatch(
                                credential -> credential.getType().equals(CredentialType.ADMINISTRATIVE)
                                        && credential.getIsActive())
                        )
                )
                        || (!isAdmin &&
                        (user.getCompany().getCredentials().stream().noneMatch(
                                credential -> credential.getType().equals(CredentialType.OPERATIONAL) && credential.getIsActive()
                        ) || user.getRoles().stream().allMatch(role -> role.getName().equals(Role.EQUALY_MASTER_ADMIN)
                                || role.getName().equals(Role.MASTER_ADMIN)
                                || role.getName().equals(Role.COMMON_ADMIN)))
                )){
            throw new BusinessException(messageSource.getMessage("error.invalid_auth_method", null, LocaleContextHolder.getLocale()));
        }
    }

    @Override
    public OAuthTokenResponse authenticateUser(Jwt token, String refreshToken) {
        String jti = new String(Base64.getDecoder().decode(refreshToken));
        if(!jti.equals(token.getId())){
            throw new BusinessException(messageSource.getMessage("error.invalid_token", null, LocaleContextHolder.getLocale()));
        }

        User user = userUseCase.getUserById(Long.valueOf(token.getClaim("sub")));
        Boolean isAdmin = user.getRoles().stream().anyMatch(role -> role.getName().equals(Role.EQUALY_MASTER_ADMIN)
                || role.getName().equals(Role.MASTER_ADMIN)
                || role.getName().equals(Role.COMMON_ADMIN));
        this.validateAuthentication(user,null, isAdmin, Boolean.TRUE);
        return jwtPort.encodeToken(isAdmin, user);
    }
}