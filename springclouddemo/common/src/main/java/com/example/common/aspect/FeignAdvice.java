package com.example.common.aspect;

import com.example.common.dto.ApiResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FeignAdvice {
    @Pointcut("@within(org.springframework.cloud.openfeign.FeignClient)")
    public void pointCut() {
    }

    @Around(value = "pointCut()")
    public ApiResult around(ProceedingJoinPoint pjp) {

        try {
            return (ApiResult) pjp.proceed();
        } catch (Throwable e) {
            return ApiResult.fail(e.getMessage());
        }
    }
}
