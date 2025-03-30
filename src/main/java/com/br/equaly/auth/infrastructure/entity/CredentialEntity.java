package com.br.equaly.auth.infrastructure.entity;

import com.br.equaly.auth.domain.enums.CredentialType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Entity(name = "Credential")
@Table(name = "CREDENTIAL", schema = "equaly_iam")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CredentialEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    private CompanyEntity companyEntity;

    @Column(name = "VALUE", nullable = false, unique = true)
    private String value;

    @Column(name = "SECRET", nullable = false, unique = true)
    private String secret;

    @Column(name = "TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private CredentialType type;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name = "DISABLED_AT")
    private LocalDateTime disabledAt;

    @Column(name = "DISABLED_BY")
    private String disabledBy;
}