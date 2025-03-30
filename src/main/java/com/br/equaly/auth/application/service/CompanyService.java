package com.br.equaly.auth.application.service;

import com.br.equaly.auth.application.port.input.CompanyUseCase;
import com.br.equaly.auth.infrastructure.entity.CompanyEntity;
import org.springframework.stereotype.Service;

@Service
public class CompanyService implements CompanyUseCase {

    @Override
    public Boolean validateCompanyEntity(CompanyEntity companyEntity) {
        if(!companyEntity.getIsActive() || companyEntity.getCredentialEntities().isEmpty()){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}