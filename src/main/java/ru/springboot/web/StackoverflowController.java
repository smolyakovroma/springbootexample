package ru.springboot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.springboot.model.StackoverflowWebsite;
import ru.springboot.service.StackoverflowService;

import java.util.List;

@RestController
@RequestMapping("/api/stackoverflow")
public class StackoverflowController {

    @Autowired
    private StackoverflowService stackoverflowService;

    @RequestMapping
    public List<StackoverflowWebsite> getListOfProviders() {
        return stackoverflowService.findAll();
    }
}
