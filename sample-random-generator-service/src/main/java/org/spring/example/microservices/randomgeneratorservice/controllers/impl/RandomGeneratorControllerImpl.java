package org.spring.example.microservices.randomgeneratorservice.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.spring.example.microservices.randomgeneratorservice.controllers.RandomGeneratorController;
import org.spring.example.microservices.randomgeneratorservice.dtos.requests.GenerateRandomSyncRequest;
import org.spring.example.microservices.randomgeneratorservice.dtos.responses.GenerateRandomSyncResponse;
import org.spring.example.microservices.randomgeneratorservice.services.RandomGeneratorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/random-generator")
@RequiredArgsConstructor
public class RandomGeneratorControllerImpl implements RandomGeneratorController {

    private final RandomGeneratorService randomGeneratorService;

    @Override
    public GenerateRandomSyncResponse generateRandomNumberSync(final GenerateRandomSyncRequest generateRandomSyncRequest) {
        return randomGeneratorService.generateRandomNumberSyncResponse(generateRandomSyncRequest);
    }
}
