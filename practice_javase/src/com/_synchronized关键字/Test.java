package com._synchronized关键字;

import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized 对象锁
 * 锁住了this 其他带锁普通方法拿不到锁
 * 但是锁住了一个成员方法就是锁住了所有其他普通成员方法，和this
 * 不是锁的同一个成员变量都不会互相影响
 *
 * synchronized 类锁 类似对象锁
 */

/**
 * @author MaoYongjie
 * @date 2022/8/10 16:58
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        TestSynchronized t1 = new TestSynchronized();
        TestSynchronized t2 = new TestSynchronized();
//        new Thread(() -> {
//            try {
//                t1.method1();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();

        new Thread(() -> {
            try {
                t1.method2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            Thread.sleep(1000);
            t1.method1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
