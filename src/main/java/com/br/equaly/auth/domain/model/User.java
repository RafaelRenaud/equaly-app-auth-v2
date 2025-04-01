package com.br.equaly.auth.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class User{

    private Long id;

    private UniversalUser universalUser;

    private Company company;

    private Department department;

    private String login;

    private String username;

    private String nickname;

    private String email;

    private String password;

    private List<UserRole> roles;

    private Boolean isActive;

    private LocalDateTime lastLogin;

    private String avatarId;

    private Audit audit;

    public User() {
    }

    public User(Long id, UniversalUser universalUser, Company company, Department department, String login, String username, String nickname, String email, String password, List<UserRole> roles, Boolean isActive, LocalDateTime lastLogin, String avatarId, Audit audit) {
        this.id = id;
        this.universalUser = universalUser;
        this.company = company;
        this.department = department;
        this.login = login;
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.isActive = isActive;
        this.lastLogin = lastLogin;
        this.avatarId = avatarId;
        this.audit = audit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UniversalUser getUniversalUser() {
        return universalUser;
    }

    public void setUniversalUser(UniversalUser universalUser) {
        this.universalUser = universalUser;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }
}
