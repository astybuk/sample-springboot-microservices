package org.spring.example.microservices.randomgeneratorservice.services.kakfka;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface KafkaConsumerService {

    void consumeRandomNumberGenerationEvent(final String requestStr) throws JsonProcessingException;
}
