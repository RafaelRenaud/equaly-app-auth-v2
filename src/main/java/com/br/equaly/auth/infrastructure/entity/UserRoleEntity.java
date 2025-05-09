package com.br.equaly.auth.infrastructure.entity;

import com.br.equaly.auth.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "COMPANY_USER_ROLE", schema = "equaly_adm", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"COMPANY_USER_ID", "NAME"}),
        @UniqueConstraint(columnNames = {"COMPANY_USER_ID", "NAME", "TYPE"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserRoleEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COMPANY_USER_ID", referencedColumnName = "ID", nullable = false)
    private UserEntity userEntity;

    @Column(name = "NAME")
    @Enumerated(EnumType.STRING)
    private Role name;
}