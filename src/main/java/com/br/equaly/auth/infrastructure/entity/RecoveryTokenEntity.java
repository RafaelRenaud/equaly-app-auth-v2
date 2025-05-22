package com.br.equaly.auth.infrastructure.entity;

import com.br.equaly.auth.domain.enums.RecoveryTokenType;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@RedisHash(value = "RecoveryTokenEntity", timeToLive = 1800L)
public class RecoveryTokenEntity implements Serializable {

    @Id
    private String id;

    private RecoveryTokenType recoveryTokenType;

    private String code;

    private Long userId;

    private String email;

    private String username;

    private String companyUsername;

    private String companyName;

    private String companyTradingName;

    private String companyAlias;

    private LocalDateTime createdAt;

    public RecoveryTokenEntity(String id, RecoveryTokenType recoveryTokenType, String code, Long userId, String email, String username, String companyUsername, String companyName, String companyTradingName, String companyAlias, LocalDateTime createdAt) {
        this.id = id;
        this.recoveryTokenType = recoveryTokenType;
        this.code = code;
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.companyUsername = companyUsername;
        this.companyName = companyName;
        this.companyTradingName = companyTradingName;
        this.companyAlias = companyAlias;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RecoveryTokenType getRecoveryTokenType() {
        return recoveryTokenType;
    }

    public void setRecoveryTokenType(RecoveryTokenType recoveryTokenType) {
        this.recoveryTokenType = recoveryTokenType;
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

    public String getCompanyTradingName() {
        return companyTradingName;
    }

    public void setCompanyTradingName(String companyTradingName) {
        this.companyTradingName = companyTradingName;
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