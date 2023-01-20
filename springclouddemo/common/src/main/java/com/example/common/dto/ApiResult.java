package com.example.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResult<T> {
    private Integer status;
    private String message;
    private T Data;

    public static <T> ApiResult<T> success(T data) {
        return ApiResult.<T>builder()
                .status(200)
                .message("请求成功")
                .Data(data)
                .build();
    }

    public static <T> ApiResult<T> fail(Integer status, String message) {
        return ApiResult.<T>builder()
                .status(status)
                .message(message)
                .Data(null)
                .build();
    }

    public static <T> ApiResult<T> fail(Integer status, String message, T data) {
        return ApiResult.<T>builder()
                .status(status)
                .message(message)
                .Data(data)
                .build();
    }


    public static <T> ApiResult<T> fail(String message) {
        return ApiResult.<T>builder()
                .status(500)
                .message(message)
                .Data(null)
                .build();
    }

    public static <T> ApiResult<T> fail(Exception ex) {
        ex.printStackTrace();
        return ApiResult.<T>builder()
                .status(500)
                .message(ex.getMessage())
                .Data(null)
                .build();
    }
}
