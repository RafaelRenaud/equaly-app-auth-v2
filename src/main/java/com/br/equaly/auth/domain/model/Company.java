package com.br.equaly.auth.domain.model;

import java.util.List;

public class Company {

    private Long id;

    private String name;

    private String alias;

    private String tradingName;

    private String displayName;

    private String document;

    private String contact;

    private Boolean isActive;

    private String logoKey;

    private Audit audit;

    private List<Credential> credentials;

    public Company(Long id, String name, String alias, String tradingName, String displayName, String document, String contact, Boolean isActive, String logoKey, Audit audit) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.tradingName = tradingName;
        this.displayName = displayName;
        this.document = document;
        this.contact = contact;
        this.isActive = isActive;
        this.logoKey = logoKey;
        this.audit = audit;
    }

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public String getLogoKey() {
        return logoKey;
    }

    public void setLogoKey(String logoKey) {
        this.logoKey = logoKey;
    }

    public List<Credential> getCredentials() {
        return credentials;
    }

    public void setCredentials(List<Credential> credentials) {
        this.credentials = credentials;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }
}
