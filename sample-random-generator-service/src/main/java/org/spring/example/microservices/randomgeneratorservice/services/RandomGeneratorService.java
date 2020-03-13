package org.spring.example.microservices.randomgeneratorservice.services;

import org.spring.example.microservices.randomgeneratorservice.dtos.requests.GenerateRandomSyncRequest;
import org.spring.example.microservices.randomgeneratorservice.dtos.responses.GenerateRandomSyncResponse;

public interface RandomGeneratorService {

    GenerateRandomSyncResponse generateRandomNumberSyncResponse(GenerateRandomSyncRequest generateRandomSyncRequest);
}
