package com.example.demo.aop.controller;

import com.example.demo.annotation.MyLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AController {

    @RequestMapping("/a/hello")
    public Object sayHello() {
        return "hello";
    }


    @MyLog(type = "error")
    @RequestMapping("/a/hi")
    public Object sayHi() {
        return "hi";
    }
}
