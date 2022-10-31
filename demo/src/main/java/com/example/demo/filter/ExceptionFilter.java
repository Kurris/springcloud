package com.example.demo.filter;

import com.example.demo.ApiResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionFilter {

    @ExceptionHandler(Exception.class)
    public ApiResult exceptionHandler(Exception e) {
        e.printStackTrace();
        return ApiResult.fail(e.getMessage(), null);
    }
}
