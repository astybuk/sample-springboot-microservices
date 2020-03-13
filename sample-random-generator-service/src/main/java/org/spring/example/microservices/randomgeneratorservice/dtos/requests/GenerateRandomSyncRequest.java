package org.spring.example.microservices.randomgeneratorservice.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateRandomSyncRequest {

    private String requestServiceName;
    private Integer randomThreshold;
}
