//package com.example.demo.aop.advice;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class AAdvice {
//    @Pointcut("execution (* com.example.demo.aop.controller.*.*(..))")
//    public void test() {
//
//    }
//
//    @Before("test()")
//    public void beforeAdvice() {
//        System.out.println("beforeAdvice...");
//    }
//
//    @After("test()")
//    public void afterAdvice() {
//        System.out.println("afterAdvice...");
//    }
//
//    @AfterReturning(value = "test()", returning = "returnObject")
//    public Object afterReturning(JoinPoint joinPoint, Object returnObject) {
//        System.out.println("afterReturning");
//
//        return returnObject;
//    }
//
//    @Around("test()")
//    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        System.out.println("before");
//        try {
//            return proceedingJoinPoint.proceed();
//        } catch (Throwable t) {
//            t.printStackTrace();
//            throw t;
//        } finally {
//            System.out.println("after");
//        }
//    }
//}
