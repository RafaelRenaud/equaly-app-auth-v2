package com.br.equaly.auth.domain.model;

import com.br.equaly.auth.domain.enums.DocumentType;

import java.io.Serializable;

public class UniversalUser {

    private Long id;

    private String name;

    private String documentNumber;

    private DocumentType documentType;

    public UniversalUser() {
    }

    public UniversalUser(Long id, String name, String documentNumber, DocumentType documentType) {
        this.id = id;
        this.name = name;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }
}