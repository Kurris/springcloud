package com.example.controller;


import com.example.common.annontaion.Authorize;
import com.example.common.dto.CurrentUserInfo;
import com.example.dto.AInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;

@RestController
@RefreshScope
public class OrderController {

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private CurrentUserInfo currentUserInfo;

    @Authorize
    @GetMapping("/getName")
    public String getName(@Validated AInputDto input) {
        return input.getName() + "/" + currentUserInfo.getUserId();
    }
}
