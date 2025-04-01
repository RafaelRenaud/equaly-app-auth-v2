package com.br.equaly.auth.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "COMPANY_USER", schema = "equaly_adm", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"USER_ID", "COMPANY_ID"}),
        @UniqueConstraint(columnNames = {"COMPANY_ID", "EMAIL"})
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false)
    private UniversalUserEntity universalUserEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COMPANY_ID", nullable = false)
    private CompanyEntity companyEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPARTMENT_ID", nullable = false)
    private DepartmentEntity departmentEntity;

    @Column(name = "LOGIN", unique = true, nullable = false)
    private String login;

    @Column(name = "COMPANY_USERNAME", nullable = false)
    private String username;

    @Column(name = "NICKNAME", nullable = false)
    private String nickname;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive;

    @Column(name = "LAST_LOGIN_AT")
    private LocalDateTime lastLogin;

    @Column(name = "AVATAR_ID")
    private String avatarId;

    @Embedded
    private AuditEntity auditEntity;
}
