package com.br.equaly.auth.infrastructure.mapper;

import com.br.equaly.auth.domain.model.Credential;
import com.br.equaly.auth.infrastructure.entity.CredentialEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CredentialMapper {

    CredentialMapper INSTANCE = Mappers.getMapper(CredentialMapper.class);

    @Mapping(source = "credentialEntity.companyEntity", target = "company")
    Credential toCredential(CredentialEntity credentialEntity);
}