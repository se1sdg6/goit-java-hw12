package com;

import java.util.concurrent.Semaphore;

public class Water {

    static Semaphore hydrogen = new Semaphore(2);
    static Semaphore oxygen = new Semaphore(1);


    public void start() {
        new H().start();
        new H().start();
        new O().start();
    }

    private static class H extends Thread {
        @Override
        public void run() {
            System.out.print('H');
            try {
                hydrogen.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hydrogen.release();
        }
    }



    private static class O extends Thread {
        @Override
        public void run() {
            System.out.print('O');
            try {
                oxygen.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            oxygen.release();
        }
    }

    public static void main(String[] args) {
        for (int i = 9; i > 0; i--) {
            new Water().start();
        }
    }
}
