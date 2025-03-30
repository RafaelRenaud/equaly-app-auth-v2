package com.br.equaly.auth.infrastructure.adapter.in.controller;

import com.br.equaly.auth.api.OauthApiDelegate;
import com.br.equaly.auth.application.port.input.AuthenticationUseCase;
import com.br.equaly.auth.model.OAuthTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController implements OauthApiDelegate {

    private final AuthenticationUseCase authenticationUseCase;

    public AuthenticationController(AuthenticationUseCase authenticationUseCase) {
        this.authenticationUseCase = authenticationUseCase;
    }

    @Override
    public ResponseEntity<OAuthTokenResponse> login(String password,
                                                    Boolean isAdmin,
                                                    Integer companyId,
                                                    String login,
                                                    String email,
                                                    String document) {
        return new ResponseEntity<>(authenticationUseCase.authenticateUser(
                password, isAdmin, companyId, login, email, document),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<OAuthTokenResponse> refreshToken(String refreshToken) {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>(authenticationUseCase.authenticateUser(authenticationToken.getToken(), refreshToken),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> validateToken() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
