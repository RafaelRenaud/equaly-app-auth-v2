package com.br.equaly.auth.application.service;

import com.br.equaly.auth.application.port.input.CredentialUseCase;
import com.br.equaly.auth.domain.model.Credential;
import com.br.equaly.auth.infrastructure.adapter.out.repository.CredentialRepository;
import com.br.equaly.auth.infrastructure.mapper.CredentialMapper;
import org.springframework.stereotype.Service;

@Service
public class CredentialService implements CredentialUseCase {

    private final CredentialRepository credentialRepository;
    private final CredentialMapper credentialMapper;

    public CredentialService(CredentialRepository credentialRepository, CredentialMapper credentialMapper) {
        this.credentialRepository = credentialRepository;
        this.credentialMapper = credentialMapper;
    }

    @Override
    public Credential getCredentialByAppkey(String appkey) {
        return credentialMapper.toCredential(credentialRepository.findByAppkey(appkey));
    }
}