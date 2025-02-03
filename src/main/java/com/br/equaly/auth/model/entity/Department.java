package com.br.equaly.auth.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "DEPARTMENT", schema = "equaly_adm")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "ID")
public class Department implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @Embedded
    private Audit audit;
}
