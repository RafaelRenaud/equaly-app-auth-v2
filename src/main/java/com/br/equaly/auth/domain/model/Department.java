package com.br.equaly.auth.domain.model;

public class Department{

    private Long id;

    private Company company;

    private String name;

    private String description;

    private Boolean isActive;

    private Audit audit;

    public Department() {
    }

    public Department(Long id, Company company, String name, String description, Boolean isActive, Audit audit) {
        this.id = id;
        this.company = company;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.audit = audit;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }
}
