package com.core.apirest.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class Controller {
    @GetMapping("/")
    public String root() {
        return "Hello World!";
    }
}
