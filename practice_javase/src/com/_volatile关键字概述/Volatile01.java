package com._volatile关键字概述;

/**
 * 目标：并发编程下，多线程访问访问变量的不可见问题
 * <p>
 * 引入：
 * 多个线程访问共享变量，会出现一个线程修改变量的值后，其他线程看不到最新值的情况
 */

public class Volatile01 extends Thread {

    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}

class VisibilityDemo{
    public static void main(String[] args) {

        //启动子线程
        Volatile01 t = new Volatile01();
        t.start();
        //
        while (true){
            if(t.isFlag()){
                System.out.println("主线程进入执行");
            }
        }
    }
}
