package org.spring.example.microservices.samplerandomgeneratorclientservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.spring.example.microservices.samplerandomgeneratorclientservice.dtos.responses.GenerateRandomResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/random-generator-client")
public interface RandomGeneratorClientController {

    @GetMapping(value = "/number", params = "sync")
    GenerateRandomResponse testRandomNumberGenerationSync(@RequestParam("randomThreshold") final Integer randomThreshold);

    @GetMapping(value = "/number", params = {"sync", "feign"})
    GenerateRandomResponse testRandomNumberGenerationFeignSync(@RequestParam("randomThreshold") final Integer randomThreshold);

    @GetMapping(value = "/number", params = "async")
    String testRandomNumberGenerationAsync(@RequestParam("randomThreshold") final Integer randomThreshold) throws InterruptedException;

    @GetMapping(value = "/number", params = {"async", "feign"})
    String testRandomNumberGenerationFeignAsync(@RequestParam("randomThreshold") final Integer randomThreshold) throws InterruptedException;

    @GetMapping(value = "/number", params = "event")
    String testRandomNumberGenerationTroughEvent(@RequestParam("randomThreshold") final Integer randomThreshold) throws JsonProcessingException;
}
