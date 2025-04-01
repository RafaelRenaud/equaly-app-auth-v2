package com.br.equaly.auth.application.port.input;

import com.br.equaly.auth.domain.model.Credential;

public interface CredentialUseCase {
    Credential getCredentialByAppkey(String appkey);
}