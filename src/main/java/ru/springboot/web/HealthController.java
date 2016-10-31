package ru.springboot.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class HealthController {

    @RequestMapping(value = "/health")
    public ResponseEntity<String> getStatus(){

        Random random = new Random();
        int randomInt = random.nextInt(2);
        if(randomInt==1){
            return new ResponseEntity<String>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("Ok", HttpStatus.OK);
    }
}
