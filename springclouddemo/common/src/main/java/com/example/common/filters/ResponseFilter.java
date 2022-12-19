package com.example.common.filters;

import com.example.common.dto.ApiResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ResponseFilter implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //处理非200httpStatus的请求
        if (((ServletServerHttpResponse) response).getServletResponse().getStatus() != 200) {
            return null;
        }

        //处理swagger请求资源
        String path = request.getURI().getPath();
        if (path.equals("/swagger-resources")||path.equals("/v3/api-docs")) {
            return body;
        }

        if (body instanceof ApiResult) {
            return body;
        }

        if (body instanceof String) {

            ApiResult<Object> value = ApiResult.success(body);

            try {
                response.getHeaders().set("Content-Type", "application/json; charset=utf-8");
                return new ObjectMapper().writeValueAsString(value);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return ApiResult.success(body);
    }
}
