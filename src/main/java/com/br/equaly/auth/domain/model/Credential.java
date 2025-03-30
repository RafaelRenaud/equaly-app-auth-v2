package com.br.equaly.auth.domain.model;

import com.br.equaly.auth.domain.enums.CredentialType;

import java.time.LocalDateTime;

public class Credential{

    private Long id;

    private Company company;

    private String value;

    private String secret;

    private CredentialType type;

    private Boolean isActive;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime disabledAt;

    private String disabledBy;

    public Credential(Long id, Company company, String value, String secret, CredentialType type, Boolean isActive, LocalDateTime createdAt, String createdBy, LocalDateTime disabledAt, String disabledBy) {
        this.id = id;
        this.company = company;
        this.value = value;
        this.secret = secret;
        this.type = type;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.disabledAt = disabledAt;
        this.disabledBy = disabledBy;
    }

    public Credential() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public CredentialType getType() {
        return type;
    }

    public void setType(CredentialType type) {
        this.type = type;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getDisabledAt() {
        return disabledAt;
    }

    public void setDisabledAt(LocalDateTime disabledAt) {
        this.disabledAt = disabledAt;
    }

    public String getDisabledBy() {
        return disabledBy;
    }

    public void setDisabledBy(String disabledBy) {
        this.disabledBy = disabledBy;
    }
}