package org.spring.example.microservices.samplerandomgeneratorclientservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.example.microservices.samplerandomgeneratorclientservice.dtos.requests.GenerateRandomRequest;
import org.spring.example.microservices.samplerandomgeneratorclientservice.dtos.responses.GenerateRandomResponse;
import org.spring.example.microservices.samplerandomgeneratorclientservice.feign.RandomGeneratorServiceFeignClient;
import org.spring.example.microservices.samplerandomgeneratorclientservice.services.RandomGeneratorAsyncService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class RandomGeneratorAsyncServiceImpl implements RandomGeneratorAsyncService {

    @Value("${spring.application.name}")
    private String microserviceName;

    @Value("${generator.service.url}")
    private String randomGeneratorServiceUrl;

    private final RestTemplate restTemplate;
    private final RandomGeneratorServiceFeignClient randomGeneratorServiceFeignClient;

    @Override
    @Async("threadPoolTaskExecutor")
    public CompletableFuture<GenerateRandomResponse> sendRandomNumberGenerationRequestAsync(final Integer randomThreshold) throws InterruptedException {
        var generateRandomRequest = new GenerateRandomRequest(microserviceName, randomThreshold);
        var destinationUrl = randomGeneratorServiceUrl + "/random-generator/number?sync";
        var responseEntity = restTemplate.postForEntity(destinationUrl, generateRandomRequest, GenerateRandomResponse.class);

        return Objects.equals(HttpStatus.OK, responseEntity.getStatusCode())
                ? CompletableFuture.completedFuture(responseEntity.getBody())
                : CompletableFuture.completedFuture(new GenerateRandomResponse());
    }

    @Override
    @Async("threadPoolTaskExecutor")
    public CompletableFuture<GenerateRandomResponse> sendRandomNumberGenerationRequestAsyncFeign(final Integer randomThreshold) throws InterruptedException {
        var generateRandomRequest = new GenerateRandomRequest(microserviceName, randomThreshold);
        var responseEntity = randomGeneratorServiceFeignClient.testRandomGenerationSync(generateRandomRequest);

        return CompletableFuture.completedFuture(responseEntity);
    }
}
