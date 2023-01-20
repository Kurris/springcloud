package com.example.controller;

import com.example.clients.UserClient;
import com.example.dto.AInputDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RefreshScope
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private UserClient userClient;

    @Value("${name}")
    private String name;
//
//    @Value("${name1}")
//    private String name1;

    @GetMapping("/getName")
    public String getName(@Validated AInputDto input) {
        return input.getName();
    }
}
