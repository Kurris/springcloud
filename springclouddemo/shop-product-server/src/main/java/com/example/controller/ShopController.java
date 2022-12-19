package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {

    @GetMapping("/get")
    public String get(){
        System.out.println("-----");
        return "aaa";
    }
}
