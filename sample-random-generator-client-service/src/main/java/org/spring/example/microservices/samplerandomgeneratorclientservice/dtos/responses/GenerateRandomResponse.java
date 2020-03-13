package org.spring.example.microservices.samplerandomgeneratorclientservice.dtos.responses;

import lombok.Data;

@Data
public class GenerateRandomResponse {

    private String requestServiceName;
    private String responseServiceName;
    private Integer randomThreshold;
    private Integer randomResult;
}
