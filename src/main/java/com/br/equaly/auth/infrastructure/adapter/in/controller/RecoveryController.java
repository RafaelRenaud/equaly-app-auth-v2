package com.br.equaly.auth.infrastructure.adapter.in.controller;

import com.br.equaly.auth.api.RecoveryApiDelegate;
import com.br.equaly.auth.application.port.input.RecoveryUseCase;
import com.br.equaly.auth.model.AccountActivationRequest;
import com.br.equaly.auth.model.RecoveryRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecoveryController implements RecoveryApiDelegate {

    private final RecoveryUseCase recoveryUseCase;

    public RecoveryController(RecoveryUseCase recoveryUseCase) {
        this.recoveryUseCase = recoveryUseCase;
    }

    @Override
    public ResponseEntity<Void> recoverAccount(String recoveryId,
                                               AccountActivationRequest accountActivationRequest) {
        recoveryUseCase.recoverAccount(recoveryId, accountActivationRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> recovery(RecoveryRequest recoveryRequest){
        recoveryUseCase.sendRecoveryToken(recoveryRequest);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
