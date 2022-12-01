package com.example.demo.thread;

import java.util.concurrent.Callable;

public class CustomThread3 implements Callable<String> {
    @Override
    public String call() {
        return  Thread.currentThread().getName() + "is running";
    }
}
