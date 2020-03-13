package org.spring.example.microservices.samplerandomgeneratorclientservice.services;

import org.spring.example.microservices.samplerandomgeneratorclientservice.dtos.responses.GenerateRandomResponse;

import java.util.concurrent.CompletableFuture;

public interface RandomGeneratorAsyncService {

    CompletableFuture<GenerateRandomResponse> sendRandomNumberGenerationRequestAsync(final Integer randomThreshold) throws InterruptedException;

    CompletableFuture<GenerateRandomResponse> sendRandomNumberGenerationRequestAsyncFeign(final Integer randomThreshold) throws InterruptedException;
}
