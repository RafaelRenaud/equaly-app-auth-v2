package com.br.equaly.auth.application.port.input;

import com.br.equaly.auth.infrastructure.entity.CompanyEntity;

public interface CompanyUseCase {
    Boolean validateCompanyEntity(CompanyEntity companyEntity);
}