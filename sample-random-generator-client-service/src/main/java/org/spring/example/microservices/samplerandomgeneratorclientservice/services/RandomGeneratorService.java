package org.spring.example.microservices.samplerandomgeneratorclientservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.spring.example.microservices.samplerandomgeneratorclientservice.dtos.responses.GenerateRandomResponse;

public interface RandomGeneratorService {

    GenerateRandomResponse testRandomNumberGeneration(final Integer randomThreshold);

    GenerateRandomResponse testRandomNumberGenerationFeign(final Integer randomThreshold);

    void testRandomNumberGenerationAsync(final Integer randomThreshold) throws InterruptedException;

    void testRandomNumberGenerationFeignAsync(final Integer randomThreshold) throws InterruptedException;

    void testRandomNumberGenerationTroughEvent(final Integer randomThreshold) throws JsonProcessingException;
}
