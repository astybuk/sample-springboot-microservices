package org.spring.example.microservices.samplerandomgeneratorclientservice.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenerateRandomRequest {

    private String requestServiceName;
    private Integer randomThreshold;
}
