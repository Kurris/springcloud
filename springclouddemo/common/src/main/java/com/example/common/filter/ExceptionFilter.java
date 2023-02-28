package com.example.common.filter;

import com.example.common.dto.ApiResult;
import com.example.common.dto.ValidationErrorDto;
import com.example.common.exception.UnauthorizedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionFilter {

    @ExceptionHandler
    public ApiResult<Object> exceptionHandler(Exception ex) {
        return ApiResult.fail(ex);
    }

    //405 处理
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public void notSupportRequestMethod(HttpRequestMethodNotSupportedException ex) throws HttpRequestMethodNotSupportedException {
        throw ex;
    }


    //400 处理
    @ExceptionHandler(value = BindException.class)
    public ApiResult<Object> validationExceptionHandler(BindException ex) {
        return ApiResult.fail(400, "参数验证失败", ex.getFieldErrors().stream().map(x -> ValidationErrorDto.builder()
                        .objectName(x.getObjectName())
                        .field(x.getField())
                        .message(x.getDefaultMessage())
                        .build())
                .collect(Collectors.toList()));
    }

    //401 处理
    @ExceptionHandler(value = UnauthorizedException.class)
    public ApiResult<Object> validationExceptionHandler(UnauthorizedException ex) {
        return ApiResult.fail(ex.getCode(), ex.getMessage());
    }


}
