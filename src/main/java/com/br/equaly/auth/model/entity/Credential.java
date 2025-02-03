package com.br.equaly.auth.model.entity;

import com.br.equaly.auth.model.enums.CredentialType;
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
@EqualsAndHashCode(of = "ID")
public class Credential implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "SECRET")
    private String secret;

    @Column(name = "TYPE")
    private CredentialType type;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "DISABLED_AT")
    private LocalDateTime disabledAt;

    @Column(name = "DISABLED_BY")
    private String disabledBy;
}
