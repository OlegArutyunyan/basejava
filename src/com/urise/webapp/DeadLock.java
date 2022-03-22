package com.urise.webapp;

import java.util.Arrays;

import static java.lang.Thread.sleep;

public class DeadLock {
    private static final Object LOCK1 = new Object();
    private static final Object LOCK2 = new Object();

    public static void main(String[] args) {
        createNewThread(LOCK1, LOCK2);
        createNewThread(LOCK2, LOCK1);
    }

    public static void createNewThread (Object lock1, Object lock2) {
        new Thread(() -> {
            try {
                operation(lock1, lock2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void operation(Object lock1, Object lock2) throws InterruptedException {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + " locks lock");
            sleep(50);
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " locks another lock");
                System.out.println("Operation method executed from " + Thread.currentThread().getName());
            }
        }
    }
}
