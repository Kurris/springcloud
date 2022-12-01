package com.example.demo.thread;

public class CustomThread1 extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "is running");
    }
}
