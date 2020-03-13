package org.spring.example.microservices.samplerandomgeneratorclientservice.feign;

import org.spring.example.microservices.samplerandomgeneratorclientservice.dtos.requests.GenerateRandomRequest;
import org.spring.example.microservices.samplerandomgeneratorclientservice.dtos.responses.GenerateRandomResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "generator-service-feign-client", url = "${generator.service.url}")
public interface RandomGeneratorServiceFeignClient {

    @PostMapping("/random-generator/number?sync")
    GenerateRandomResponse testRandomGenerationSync(@RequestBody final GenerateRandomRequest generateRandomRequest);
}
