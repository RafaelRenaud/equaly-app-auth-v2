package com.br.equaly.auth.application.port.input;

import com.br.equaly.auth.model.OAuthTokenResponse;
import org.springframework.security.oauth2.jwt.Jwt;

public interface AuthenticationUseCase {
    OAuthTokenResponse authenticateUser(String password,
                                        Boolean isAdmin,
                                        Integer companyId,
                                        String login,
                                        String email,
                                        String document);

    OAuthTokenResponse authenticateUser(Jwt token, String refreshToken);
}