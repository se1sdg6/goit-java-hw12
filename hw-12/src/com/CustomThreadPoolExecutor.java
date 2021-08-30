package com;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPoolExecutor extends ThreadPoolExecutor {

    public CustomThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize, 10, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
    }

    @Override
    public void execute(Runnable command) {
        int count = command.getClass().getAnnotation(Repeat.class).executions();

        for (; 0 < count; count--) super.execute(command);
    }

    public static void main(String[] args) {
        CustomThreadPoolExecutor customThreadPoolExecutor = new CustomThreadPoolExecutor(1);
        customThreadPoolExecutor.execute(new MyRunnable());
        customThreadPoolExecutor.shutdown();
    }
}
