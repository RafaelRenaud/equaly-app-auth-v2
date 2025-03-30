package com.br.equaly.auth.application.port.input;

import com.br.equaly.auth.domain.model.User;
import com.br.equaly.auth.model.CompaniesResponse;
import org.springframework.data.domain.Pageable;

public interface UserUseCase {
    CompaniesResponse listCompanies(String userId,
                                    String alias,
                                    String document,
                                    String status,
                                    Pageable pageable);
    User getUser(Integer companyId, String login, String email, String document);
    User getUserById(Long id);
    User loadUserByUsername(String username);
    User getUserByDocument(String document, Long organizationId);
    User getUserByEmail(String email, Long organizationId);
    void updateUserPassword(User user, String newPassword);
}