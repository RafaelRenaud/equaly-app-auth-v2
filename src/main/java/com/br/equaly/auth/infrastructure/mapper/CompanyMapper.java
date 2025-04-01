package com.br.equaly.auth.infrastructure.mapper;

import com.br.equaly.auth.infrastructure.entity.CompanyEntity;
import com.br.equaly.auth.model.CompaniesResponse;
import com.br.equaly.auth.model.Company;
import com.br.equaly.auth.model.PageableTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Mapping(source = "companyEntity.auditEntity", target = "audit")
    @Mapping(source = "companyEntity.credentialEntities", target = "credentials")
    com.br.equaly.auth.domain.model.Company toCompany(CompanyEntity companyEntity);

    @Mapping(source = "companyEntity.auditEntity", target = "audit")
    @Mapping(source = "companyEntity.credentialEntities", target = "credentials")
    List<com.br.equaly.auth.domain.model.Company> toCompany(List<CompanyEntity> companyEntity);

    @Mapping(source = "company.logoKey", target = "logoUri")
    @Mapping(expression = "java(company.getIsActive() ? \"ACTIVE\" : \"INACTIVE\")", target = "status")
    Company toCompanyDTO(com.br.equaly.auth.domain.model.Company company);

    @Mapping(source = "companies.logoKey", target = "logoUri")
    @Mapping(expression = "java(companies.getIsActive() ? \"ACTIVE\" : \"INACTIVE\")", target = "status")
    List<Company> toCompanyDTO(List<com.br.equaly.auth.domain.model.Company> companies);

    @Mapping(source = "companies.content", target = "companies")
    @Mapping(source = "companies", target = "pageable",  qualifiedByName = "toPageableDTO")
    CompaniesResponse toCompaniesResponseDTO(Page<com.br.equaly.auth.domain.model.Company> companies);

    @Named("toPageableDTO")
    default PageableTemplate toPageableDTO(Page<?> page) {
        PageableTemplate pageableTemplate = new PageableTemplate();
        pageableTemplate.setNumber(page.getNumber());
        pageableTemplate.setSize(page.getSize());
        pageableTemplate.setTotalElements(Integer.valueOf(String.valueOf(page.getTotalElements())));
        pageableTemplate.setTotalPages(page.getTotalPages());
        return pageableTemplate;
    }
}