package org.spring.example.microservices.randomgeneratorservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.example.microservices.randomgeneratorservice.dtos.requests.GenerateRandomSyncRequest;
import org.spring.example.microservices.randomgeneratorservice.dtos.responses.GenerateRandomSyncResponse;
import org.spring.example.microservices.randomgeneratorservice.services.RandomGeneratorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class RandomGeneratorServiceImpl implements RandomGeneratorService {

    @Value("${spring.application.name}")
    private String microserviceName;

    @Override
    public GenerateRandomSyncResponse generateRandomNumberSyncResponse(GenerateRandomSyncRequest generateRandomSyncRequest) {

        log.info("Request received from {}. Random number threshold: {}", generateRandomSyncRequest.getRequestServiceName(), generateRandomSyncRequest.getRandomThreshold());

        Integer randomResult = new Random().nextInt(generateRandomSyncRequest.getRandomThreshold());

        log.info("Generated value: {} will be returned to {}", randomResult, generateRandomSyncRequest.getRequestServiceName());

        return new GenerateRandomSyncResponse(generateRandomSyncRequest, microserviceName, randomResult);
    }
}
