package com.br.equaly.auth.model.entity;

import com.br.equaly.auth.model.enums.Role;
import com.br.equaly.auth.model.enums.SubRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USER", schema = "equaly_adm")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "ID")
public class User implements UserDetails, Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Role.class)
    @Column(name = "ROLE")
    private List<Role> role;

    @Enumerated(EnumType.STRING)
    @Column(name = "SUBROLE")
    @ElementCollection(targetClass = SubRole.class)
    private List<SubRole> subrole;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @Column(name = "LAST_LOGIN_AT")
    private LocalDateTime lastLogin;

    @Column(name = "AVATAR_ID")
    private String avatarId;

    @Embedded
    private Audit audit;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive && lastLogin != null;
    }

    @Override
    public boolean isAccountNonLocked() {
        return lastLogin != null;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive && lastLogin != null;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
