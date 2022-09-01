package com._线程池;


import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class ThreadPoolDemo01 extends AbstractQueuedSynchronizer {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        Executor executor = new ThreadPoolExecutor(5,10,50L,TimeUnit.MICROSECONDS,new ArrayBlockingQueue<>(5));

        Runnable target = new MyRunnable();
        executor.execute(target);
//        pool.submit(target); //创建新线程
//        pool.submit(target); //创建新线程
//        pool.submit(target); //创建新线程
//        pool.submit(target); //复用之前的线程

        ThreadPoolDemo01 threadPoolDemo01 = new ThreadPoolDemo01();

        Future<String> v1 = pool.submit(new MyCallable(100));
        Future<String> v2 = pool.submit(new MyCallable(200));
        Future<String> v3 = pool.submit(new MyCallable(300));
        Future<String> v4 = pool.submit(new MyCallable(400));
        try {
            System.out.println(v1.get());
            System.out.println(v2.get());
            System.out.println(v3.get());
            System.out.println(v4.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "===>" + i);
        }
    }
}

class MyCallable implements Callable<String> {

    private int n;

    public MyCallable(int n) {
        this.n = n;
    }

    // 需求：使用线程池，计算出1-100,1-200,1-300的和返回
    @Override
    public String call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return Thread.currentThread().getName() + "执行的结果是：" + sum;
    }
}