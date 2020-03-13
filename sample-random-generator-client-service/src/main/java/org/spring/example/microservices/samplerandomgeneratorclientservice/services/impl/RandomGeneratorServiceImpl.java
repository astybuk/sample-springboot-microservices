package org.spring.example.microservices.samplerandomgeneratorclientservice.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.example.microservices.samplerandomgeneratorclientservice.dtos.requests.GenerateRandomRequest;
import org.spring.example.microservices.samplerandomgeneratorclientservice.dtos.responses.GenerateRandomResponse;
import org.spring.example.microservices.samplerandomgeneratorclientservice.feign.RandomGeneratorServiceFeignClient;
import org.spring.example.microservices.samplerandomgeneratorclientservice.services.RandomGeneratorAsyncService;
import org.spring.example.microservices.samplerandomgeneratorclientservice.services.RandomGeneratorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@EnableFeignClients
@Slf4j
public class RandomGeneratorServiceImpl implements RandomGeneratorService {

    @Value("${spring.application.name}")
    private String microserviceName;

    @Value("${generator.service.url}")
    private String randomGeneratorServiceUrl;

    @Value(value = "${kafka.test.topic.name}")
    private String kafkaTestTopicName;


    private final RestTemplate restTemplate;
    private final RandomGeneratorServiceFeignClient randomGeneratorServiceFeignClient;
    private final RandomGeneratorAsyncService randomGeneratorAsyncService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public GenerateRandomResponse testRandomNumberGeneration(final Integer randomThreshold) {

        log.info("Generate random request for restTemplate. Random number threshold: {}", randomThreshold);

        var generateRandomRequest = new GenerateRandomRequest(microserviceName, randomThreshold);
        var destinationUrl = randomGeneratorServiceUrl + "/random-generator/number?sync";

        var responseEntity = restTemplate.postForEntity(destinationUrl, generateRandomRequest, GenerateRandomResponse.class);

        GenerateRandomResponse result;

        if (Objects.equals(HttpStatus.OK, responseEntity.getStatusCode())) {
            result = responseEntity.getBody();
            log.info("Response from {} was received. Generated number: {}", result.getResponseServiceName(), result.getRandomResult());
        } else {
            result = new GenerateRandomResponse();
            log.info("Error in report generator service. Error code: {}", responseEntity.getStatusCode());
        }

        return result;
    }

    @Override
    public GenerateRandomResponse testRandomNumberGenerationFeign(final Integer randomThreshold) {
        var generateRandomRequest = new GenerateRandomRequest(microserviceName, randomThreshold);

        log.info("Generate random request for feign client. Random number threshold: {}", randomThreshold);

        GenerateRandomResponse result = randomGeneratorServiceFeignClient.testRandomGenerationSync(generateRandomRequest);

        log.info("Response from {} was received. Generated number: {}", result.getResponseServiceName(), result.getRandomResult());

        return result;
    }

    @Override
    public void testRandomNumberGenerationAsync(final Integer randomThreshold) throws InterruptedException {

        log.info("Generate random request for restTemplate(ASYNC). Random number threshold: {}", randomThreshold);

        randomGeneratorAsyncService.sendRandomNumberGenerationRequestAsync(randomThreshold)
                .thenAccept(generateRandomResponse -> {
                    log.info("Response from {} was received. Generated number: {}", generateRandomResponse.getResponseServiceName(), generateRandomResponse.getRandomResult());
                });

        log.info("Request with random number threshold={} was sent by restTemplate (ASYNC).", randomThreshold);
    }

    @Override
    public void testRandomNumberGenerationFeignAsync(final Integer randomThreshold) throws InterruptedException {

        log.info("Generate random request for feignClient(ASYNC). Random number threshold: {}", randomThreshold);

        randomGeneratorAsyncService.sendRandomNumberGenerationRequestAsyncFeign(randomThreshold)
                .thenAccept(generateRandomResponse -> {
                    log.info("Response from {} was received. Generated number: {}", generateRandomResponse.getResponseServiceName(), generateRandomResponse.getRandomResult());
                });

        log.info("Request with random number threshold={} was sent by feignClient (ASYNC).", randomThreshold);
    }

    @Override
    public void testRandomNumberGenerationTroughEvent(final Integer randomThreshold) throws JsonProcessingException {

        log.info("Generate random request for Kafka. Random number threshold: {}", randomThreshold);

        var generateRandomRequest = new GenerateRandomRequest(microserviceName, randomThreshold);
        kafkaTemplate.send(kafkaTestTopicName, new ObjectMapper().writeValueAsString(generateRandomRequest));

        log.info("Message with request with random number threshold={} was sent to Kafka.", randomThreshold);

    }
}
