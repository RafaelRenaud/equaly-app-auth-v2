package com.br.equaly.auth.infrastructure.mapper;

import com.br.equaly.auth.domain.model.User;
import com.br.equaly.auth.infrastructure.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "userEntity.universalUserEntity", target = "universalUser")
    @Mapping(source = "userEntity.companyEntity", target = "company")
    @Mapping(source = "userEntity.departmentEntity", target = "department")
    @Mapping(source = "userEntity.auditEntity", target = "audit")
    @Mapping(source = "userEntity.companyEntity.credentialEntities", target = "company.credentials")
    User toUserDTO(UserEntity userEntity);
}