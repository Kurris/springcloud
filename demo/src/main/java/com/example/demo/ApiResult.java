package com.example.demo;

import lombok.Data;
import lombok.var;
import org.springframework.lang.Nullable;

import java.io.Serializable;

@Data
public class ApiResult<T> implements Serializable {

    private int status;
    private String message;
    private T data;

    public static <T> ApiResult ok(T data) {
        var apiResult = new ApiResult<>();
        apiResult.data = data;
        apiResult.status = 200;
        apiResult.message = "success";
        return apiResult;
    }


    public static <T> ApiResult fail(String message, @Nullable Object data) {
        var apiResult = new ApiResult<>();
        apiResult.data = data;
        apiResult.status = 500;
        apiResult.message = message;
        return apiResult;
    }
}
