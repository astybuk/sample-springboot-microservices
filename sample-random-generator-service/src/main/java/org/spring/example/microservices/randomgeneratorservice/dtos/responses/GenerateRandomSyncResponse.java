package org.spring.example.microservices.randomgeneratorservice.dtos.responses;

import lombok.Data;
import org.spring.example.microservices.randomgeneratorservice.dtos.requests.GenerateRandomSyncRequest;

@Data
public class GenerateRandomSyncResponse {

    private String  requestServiceName;
    private String responseServiceName;
    private Integer randomThreshold;
    private Integer randomResult;

    public GenerateRandomSyncResponse(GenerateRandomSyncRequest generateRandomSyncRequest, String responseServiceName, Integer randomResult) {
        this.requestServiceName = generateRandomSyncRequest.getRequestServiceName();
        this.responseServiceName = responseServiceName;
        this.randomThreshold = generateRandomSyncRequest.getRandomThreshold();
        this.randomResult = randomResult;
    }
}
