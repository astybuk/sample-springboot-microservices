package org.spring.example.microservices.randomgeneratorservice.services.kakfka.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.example.microservices.randomgeneratorservice.dtos.requests.GenerateRandomSyncRequest;
import org.spring.example.microservices.randomgeneratorservice.services.RandomGeneratorService;
import org.spring.example.microservices.randomgeneratorservice.services.kakfka.KafkaConsumerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final RandomGeneratorService randomGeneratorService;

    @Override
    @KafkaListener(id = "${kafka.test.group-id}", topics = "${kafka.test.topic.name}")
    public void consumeRandomNumberGenerationEvent(final String requestStr) throws JsonProcessingException {
        log.info("Received message from kafka...");
        GenerateRandomSyncRequest generateRandomSyncRequest = new ObjectMapper().readValue(requestStr, GenerateRandomSyncRequest.class);
        randomGeneratorService.generateRandomNumberSyncResponse(generateRandomSyncRequest);
    }
}
