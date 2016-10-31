package ru.springboot.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HealthService {

    private RestTemplate restTemplate = new RestTemplate();

    private int countAttempt;
    public void clearAttempt(){
        countAttempt=0;
    }
    @Retryable(maxAttempts = 3, value = RuntimeException.class, backoff = @Backoff(delay = 500, multiplier = 2))
    public String getHealth(){
        countAttempt++;
        return restTemplate.getForObject("http://localhost:8080/health", String.class)+" - "+countAttempt;
    }

    @Recover
    public String recover(){
        return "not ok";
    }
}
