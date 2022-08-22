package com._synchronized关键字;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author MaoYongjie
 * @date 2022/8/10 16:58
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        TestSynchronized t1 = new TestSynchronized();
        TestSynchronized t2 = new TestSynchronized();
        new Thread(() -> {
            try {
                t1.method1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        try {
            Thread.sleep(1000);
            t1.mth();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ReentrantLock lock = new ReentrantLock();
//        new Thread(() -> {
//            try {
//                t1.method2();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();

//        new Thread(() -> {
//            try {
//                TestSynchronized.staticMehod2();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        new Thread(() -> {
//            try {
//                TestSynchronized.staticMehod1();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
    }
}
