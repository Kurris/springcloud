package com.example.demo.thread;

public class CustomThread2 implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "is running");
    }
}
