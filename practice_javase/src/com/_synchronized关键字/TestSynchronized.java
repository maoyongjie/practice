package com._synchronized关键字;

/**
 * @author MaoYongjie
 * @date 2022/8/10 16:55
 * @Description:
 */
public class  TestSynchronized {
    private static final Object LOCK1 = new Object();
    private final Object LOCK2 = new Object();

    public static void staticMehod1() throws InterruptedException {
        synchronized (TestSynchronized.class){
            Thread.sleep(3000);
            System.out.println("静态方法1执行了");
        }



    }

    public static synchronized void staticMehod2() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("静态方法2执行了");
    }

    public synchronized void method1() throws InterruptedException {
            Thread.sleep(3000);
            System.out.println("普通方法1执行了");


    }

    public  void method2() throws InterruptedException {
        synchronized (LOCK2) {
            Thread.sleep(3000);
            System.out.println("普通方法2执行了");
        }
    }

    public void mth() {
        System.out.println("hehhehe");
    }

}
