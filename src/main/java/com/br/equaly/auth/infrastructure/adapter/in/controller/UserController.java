package com.br.equaly.auth.infrastructure.adapter.in.controller;

import com.br.equaly.auth.api.UserApiDelegate;
import com.br.equaly.auth.application.port.input.UserUseCase;
import com.br.equaly.auth.model.CompaniesResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApiDelegate {

    private final UserUseCase companyService;

    public UserController(UserUseCase companyService) {
        this.companyService = companyService;
    }

    @Override
    public ResponseEntity<CompaniesResponse> getCompanies(String userId,
                                                          String alias,
                                                          String document,
                                                          String status,
                                                          Pageable pageable) {
        return new ResponseEntity<>(
                companyService.listCompanies(userId,alias,document,status, pageable),
                HttpStatus.OK);
    }
}