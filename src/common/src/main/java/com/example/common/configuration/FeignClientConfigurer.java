package com.example.common.configuration;

import com.example.common.dto.ApiResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import feign.*;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
@Configuration
public class FeignClientConfigurer {
//    @Bean
//    public RequestInterceptor feignInterceptor() {
//        return new FeignInterceptor();
//    }

    @Bean
//    @ConditionalOnProperty(value = "dy-micro-service-common.feign.debug", havingValue = "true")
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default();
    }

    @Bean
    public RequestInterceptor cameraSign() {
        return template -> {

            //添加当前请求的bearer token
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                String bearerToken = attributes.getRequest().getHeader("Authorization");
                template.header("Authorization", bearerToken);
            }
            //template.body(Request.Body.encoded(newBody.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
        };
    }

    @Bean
    public ErrorDecoder feignError() {
        return (key, response) -> {

            Exception exception = null;
            try {
                // 获取原始的返回内容
                String json = Util.toString(response.body().asReader(Charset.defaultCharset()));
                exception = new RuntimeException(json);
                // 将返回内容反序列化为Result，这里应根据自身项目作修改
                ApiResult result = new ObjectMapper().readValue(json, ApiResult.class);
                // 业务异常抛出简单的 RuntimeException，保留原来错误信息
                if (result.getStatus() != 200) {
                    exception = new RuntimeException(result.getMessage());
                }
            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);
            }
            return exception;

            // 其他异常交给Default去解码处理
            // 这里使用单例即可，Default不用每次都去new
//            return new ErrorDecoder.Default().decode(key, response);
        };
    }
}
