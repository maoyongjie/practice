package com._线程通信;

public class ThreadCommunication {
    public static void main(String[] args) {
        //创建一个共享账户
        Account acc = new Account("ICBC-111",0);

        new DrewThread(acc,"小明").start();
        new DrewThread(acc,"小红").start();

        new SaveThread(acc,"亲爹").start();
        new SaveThread(acc,"岳父").start();
        new SaveThread(acc,"义父").start();

    }
}
