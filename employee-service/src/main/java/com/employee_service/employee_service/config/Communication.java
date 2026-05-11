package com.employee_service.employee_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Communication {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
