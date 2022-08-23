package com._lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class LearnLock {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(false);

        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            printCondition(lock, c1, c2);
            System.out.println(Thread.currentThread().getName() + " acquire lock");
        }, "t1").start();

        Thread.sleep(100);
        new Thread(() -> {
            System.out.println("进入t2");
            lock.lock();
            printCondition(lock, c1, c2);
            System.out.println(Thread.currentThread().getName() + " acquire lock");
        }, "t2").start();

        Thread.sleep(100);
        new Thread(() -> {
            System.out.println("进入t3");
            lock.lock();
            printCondition(lock, c1, c2);
            System.out.println(Thread.currentThread().getName() + " acquire lock");
        }, "t3").start();

        Thread.sleep(100);
        new Thread(() -> {
            System.out.println("进入t4");
            lock.lock();
            printCondition(lock, c1, c2);
            System.out.println(Thread.currentThread().getName() + " acquire lock");
        }, "t4").start();
    }

    private static void printCondition(ReentrantLock lock, Condition c1, Condition c2) {
        System.out.println("HoldCount:" + lock.getHoldCount());
        System.out.println("QueueLength:" + lock.getQueueLength());
        System.out.println("WaitQueueLength c1:" + lock.getWaitQueueLength(c1));
        System.out.println("WaitQueueLength c2:" + lock.getWaitQueueLength(c2));
    }
}
