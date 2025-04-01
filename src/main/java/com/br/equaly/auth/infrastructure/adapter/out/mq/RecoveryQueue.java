package com.br.equaly.auth.infrastructure.adapter.out.mq;

import com.azure.storage.queue.QueueClient;
import com.br.equaly.auth.application.port.output.RecoveryQueuePort;
import com.br.equaly.auth.infrastructure.entity.RecoveryTokenEntity;
import com.br.equaly.auth.infrastructure.exception.BusinessException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class RecoveryQueue implements RecoveryQueuePort {

    private final QueueClient queueClient;
    private final ObjectMapper objectMapper;

    private RecoveryQueue(QueueClient queueClient, ObjectMapper objectMapper) {
        this.queueClient = queueClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public void sendRecoveryEmail(RecoveryTokenEntity recoveryToken){
        try {
            queueClient.sendMessage(objectMapper.writeValueAsString(recoveryToken));
        } catch (JsonProcessingException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}