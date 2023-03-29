package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    @GetMapping("/ticket/value")
    public String getValue(@RequestParam(required = false) String value) {
        return value;
    }
}
