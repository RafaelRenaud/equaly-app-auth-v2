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

    private LocalDateTime createdAt;

    public RecoveryTokenEntity(String id, String code, Long userId, String email, LocalDateTime createdAt) {
        this.id = id;
        this.code = code;
        this.userId = userId;
        this.email = email;
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}