package com.br.equaly.auth.infrastructure.entity;

import jakarta.persistence.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@RedisHash(value = "RecoveryTokenEntity", timeToLive = 1800L)
public class RecoveryTokenEntity implements Serializable {

    @Id
    private String id;

    private String code;

    private Long userId;

    private String email;

    private String username;

    private String companyUsername;

    private String companyName;

    private String companyDisplayName;

    private String companyAlias;

    private LocalDateTime createdAt;

    public RecoveryTokenEntity(String id, String code, Long userId, String email, String username, String companyUsername, String companyName, String companyDisplayName, String companyAlias, LocalDateTime createdAt) {
        this.id = id;
        this.code = code;
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.companyUsername = companyUsername;
        this.companyName = companyName;
        this.companyDisplayName = companyDisplayName;
        this.companyAlias = companyAlias;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyUsername() {
        return companyUsername;
    }

    public void setCompanyUsername(String companyUsername) {
        this.companyUsername = companyUsername;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDisplayName() {
        return companyDisplayName;
    }

    public void setCompanyDisplayName(String companyDisplayName) {
        this.companyDisplayName = companyDisplayName;
    }

    public String getCompanyAlias() {
        return companyAlias;
    }

    public void setCompanyAlias(String companyAlias) {
        this.companyAlias = companyAlias;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}