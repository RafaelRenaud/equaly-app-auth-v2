package com.br.equaly.auth.application.port.output;

import com.br.equaly.auth.infrastructure.entity.RecoveryTokenEntity;

public interface RecoveryQueuePort {
    void sendRecoveryEmail(RecoveryTokenEntity recoveryToken);
}