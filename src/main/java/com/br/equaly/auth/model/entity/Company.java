package com.br.equaly.auth.model.entity;

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
@EqualsAndHashCode(of = "ID")
public class Company implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ALIAS")
    private String alias;

    @Column(name = "TRADING_NAME")
    private String tradingName;

    @Column(name = "DISPLAY_NAME")
    private String displayName;

    @Column(name = "DOCUMENT")
    private String document;

    @Column(name = "CONTACT")
    private String contact;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @Column(name = "LOGO_KEY")
    private String logoKey;

    @OneToMany(mappedBy = "company")
    private List<Credential> credentials;

    @Embedded
    private Audit audit;
}
