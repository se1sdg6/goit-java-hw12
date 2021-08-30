package com;

@Repeat(executions = 3)
public class MyRunnable implements Runnable {
    public void printHello() {
        System.out.println("Hello!");
    }

    @Override
    public void run() {
        printHello();
    }
}
