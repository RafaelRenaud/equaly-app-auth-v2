package com.br.equaly.auth.infrastructure.config.authority;

import org.springframework.security.core.GrantedAuthority;

public class EqualyGrantedAuthority implements GrantedAuthority {

    private final String role;

    public EqualyGrantedAuthority(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
