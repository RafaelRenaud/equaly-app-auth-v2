package com.br.equaly.auth.application.port.input;

import com.br.equaly.auth.model.AccountActivationRequest;
import com.br.equaly.auth.model.RecoveryRequest;

public interface RecoveryUseCase {
    void sendRecoveryToken(RecoveryRequest recoveryRequest);
    void recoverAccount(String recoveryId,
                        AccountActivationRequest accountActivationRequest);
}