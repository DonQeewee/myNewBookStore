package com.luv2code.sam.training.NewBookstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/training")

public class TestController {

    @GetMapping ("/hello")
    public String helloWorld() {
        return "Hello, Spring Boot!";
    }

}
