package org.spring.example.microservices.samplerandomgeneratorclientservice.controllers.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.spring.example.microservices.samplerandomgeneratorclientservice.controllers.RandomGeneratorClientController;
import org.spring.example.microservices.samplerandomgeneratorclientservice.dtos.responses.GenerateRandomResponse;
import org.spring.example.microservices.samplerandomgeneratorclientservice.services.RandomGeneratorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/random-generator-client")
@RequiredArgsConstructor
public class RandomGeneratorClientControllerImpl implements RandomGeneratorClientController {

    private final RandomGeneratorService randomGeneratorService;


    @Override
    public GenerateRandomResponse testRandomNumberGenerationSync(final Integer randomThreshold) {
        return randomGeneratorService.testRandomNumberGeneration(randomThreshold);
    }

    @Override
    public GenerateRandomResponse testRandomNumberGenerationFeignSync(final Integer randomThreshold) {
        return randomGeneratorService.testRandomNumberGenerationFeign(randomThreshold);
    }

    @Override
    public String testRandomNumberGenerationAsync(final Integer randomThreshold) throws InterruptedException {

        randomGeneratorService.testRandomNumberGenerationAsync(randomThreshold);

        return "Async request restTemplate - OK";
    }

    @Override
    public String testRandomNumberGenerationFeignAsync(final Integer randomThreshold) throws InterruptedException {

        randomGeneratorService.testRandomNumberGenerationFeignAsync(randomThreshold);

        return "Async request feign - OK";
    }

    @Override
    public String testRandomNumberGenerationTroughEvent(final Integer randomThreshold) throws JsonProcessingException {

        randomGeneratorService.testRandomNumberGenerationTroughEvent(randomThreshold);

        return "Message to kafka sent - OK";
    }
}
