package com.example.demo.com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping(value = "/main/index")
    public String index() {
        return "This is Main.index controller.";
    }
}
