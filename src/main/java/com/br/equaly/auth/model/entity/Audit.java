package com.br.equaly.auth.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class Audit {

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "DISABLED_AT")
    private LocalDateTime disabledAt;

    @Column(name = "DISABLED_BY")
    private String disabledBy;
}
