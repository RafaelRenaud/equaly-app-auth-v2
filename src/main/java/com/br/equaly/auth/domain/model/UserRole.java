package com.br.equaly.auth.domain.model;

import com.br.equaly.auth.domain.enums.Role;

import java.io.Serializable;

public class UserRole implements Serializable {

    private Long id;

    private User user;

    private Role name;

    public UserRole() {
    }

    public UserRole(Long id, User user, Role name) {
        this.id = id;
        this.user = user;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getName() {
        return name;
    }

    public void setName(Role name) {
        this.name = name;
    }
}
