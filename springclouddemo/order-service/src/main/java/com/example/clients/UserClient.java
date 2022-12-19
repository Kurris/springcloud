package com.example.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("shopservice")
public interface UserClient {
    @GetMapping("/get")
    String getName();
}
