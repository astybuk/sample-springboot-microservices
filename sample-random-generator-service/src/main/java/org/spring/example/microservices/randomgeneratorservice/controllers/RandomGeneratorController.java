package org.spring.example.microservices.randomgeneratorservice.controllers;

import org.spring.example.microservices.randomgeneratorservice.dtos.requests.GenerateRandomSyncRequest;
import org.spring.example.microservices.randomgeneratorservice.dtos.responses.GenerateRandomSyncResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/random-generator")
public interface RandomGeneratorController {

    @PostMapping(value = "/number", params = "sync")
    GenerateRandomSyncResponse generateRandomNumberSync(@RequestBody final GenerateRandomSyncRequest generateRandomSyncRequest);
}