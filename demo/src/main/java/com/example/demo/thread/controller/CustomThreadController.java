package com.example.demo.thread.controller;

import com.example.demo.thread.CustomThread1;
import com.example.demo.thread.CustomThread2;
import com.example.demo.thread.CustomThread3;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@RestController
@RequestMapping(value = "customThread")
public class CustomThreadController {

    @GetMapping(value = "print1")
    public void  PrintThread1(){
        for (int i = 0; i < 3; i++) {
            new CustomThread1().start();
        }
    }

    @GetMapping(value = "print2")
    public void  PrintThread2(){
        for (int i = 0; i < 3; i++) {
            new Thread(new CustomThread2(),i+"线程").start();
        }
    }


    @GetMapping(value = "print3")
    public String  PrintThread3() throws ExecutionException, InterruptedException {
        CustomThread3 customThread3 = new CustomThread3();

        FutureTask<String> task  = new FutureTask<String>(customThread3);
        new Thread(task).start();

        return task.get();
    }

}
