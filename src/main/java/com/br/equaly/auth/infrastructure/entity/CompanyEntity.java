package com.br.equaly.auth.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity(name = "Company")
@Table(name = "COMPANY", schema = "equaly_iam")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CompanyEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "ALIAS", unique = true, nullable = false)
    private String alias;

    @Column(name = "TRADING_NAME", unique = true, nullable = false)
    private String tradingName;

    @Column(name = "DISPLAY_NAME", nullable = false)
    private String displayName;

    @Column(name = "DOCUMENT", unique = true, nullable = false)
    private String document;

    @Column(name = "CONTACT", nullable = false)
    private String contact;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive;

    @Column(name = "LOGO_KEY")
    private String logoKey;

    @OneToMany(mappedBy = "companyEntity", fetch = FetchType.EAGER)
    private List<CredentialEntity> credentialEntities;

    @Embedded
    private AuditEntity auditEntity;
}
