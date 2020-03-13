package org.spring.example.microservices.samplerandomgeneratorclientservice.configurations;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("org.spring.example.microservices.samplerandomgeneratorclientservice.feign")
public class FeignConfiguration {
}
