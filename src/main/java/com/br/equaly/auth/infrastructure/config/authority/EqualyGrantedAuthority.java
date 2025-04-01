package com.br.equaly.auth.infrastructure.config.authority;

import org.springframework.security.core.GrantedAuthority;

public class EqualyGrantedAuthority implements GrantedAuthority {

    private final String name;
    private final String type;

    public EqualyGrantedAuthority(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    public String getType() {
        return type;
    }
}
