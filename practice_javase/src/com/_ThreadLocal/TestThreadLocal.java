package com._ThreadLocal;

public class TestThreadLocal {

    public static ThreadLocal<String> s = new ThreadLocal<>();

    public static ThreadLocal<String> t = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        s.set("enen");

        new Thread(()->{
            s.set("1111");
            System.out.println("子线程:"+s.get());
        }).start();

        Thread thread2  = Thread.currentThread();
//        Thread.sleep(1000);
        System.out.println(s.get());
    }
}
