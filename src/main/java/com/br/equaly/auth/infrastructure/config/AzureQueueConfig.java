package com.br.equaly.auth.infrastructure.config;

import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.QueueServiceClient;
import com.azure.storage.queue.QueueServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureQueueConfig {

    @Value("${azure.storage.queue.connection-string}")
    private String connectionString;

    @Value("${azure.storage.queue.name}")
    private String queueName;

    @Bean
    public QueueClient queueClient() {
        QueueServiceClient serviceClient = new QueueServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
        return serviceClient.getQueueClient(queueName);
    }
}
