package ru.springboot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.springboot.service.HealthService;

@RestController
public class HealthCheck {

    @Autowired
    HealthService healthService;

    @RequestMapping("/check")
    public String checkStatus(){
        healthService.clearAttempt();
        return healthService.getHealth();
    }
}
