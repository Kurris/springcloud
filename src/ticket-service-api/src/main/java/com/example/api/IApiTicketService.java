package com.example.api;

import com.example.common.dto.ApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("ticket-service")
public interface IApiTicketService {

    @GetMapping("/ticket/value")
    ApiResult<String> getName(@RequestParam(required = false) String value);
}