package com.br.equaly.auth.application.port.output;

import com.br.equaly.auth.model.CompaniesResponse;
import org.springframework.data.domain.Pageable;

public interface UserCustomPort {
    CompaniesResponse findCompniesByUserDocument(String userId, String alias, String document, String status, Pageable pageable);
}