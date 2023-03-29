package com.example.api;

import com.example.common.dto.ApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


//@FeignClient(name = "xxxClient" , url = "http://localhost:50002") 不使用服务发现的情况
@FeignClient("ticket-service")
public interface IApiTicketService {

    @GetMapping("/ticket/value")
    ApiResult<String> getName(@RequestParam(required = false) String value);
}
