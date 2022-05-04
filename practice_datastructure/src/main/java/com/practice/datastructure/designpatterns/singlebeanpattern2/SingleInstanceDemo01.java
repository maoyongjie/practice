package com.practice.datastructure.designpatterns.singlebeanpattern2;

/**
 目标： 单例设计模式

 什么是单例？
 单例的意思是一个类永远只存在一个对象
 为什么要用单例？
 开发中有很多类的对象我们只需要一个，例如虚拟机！任务管理器！
 如何实现单例？
 单例的实现方式目前有两种:
 (1) 饿汉单例设计模式：
 -- 通过类获取单例对象的时候，对象已经提前做好了
 -- 实习步骤：
    1.定义一个单例类
    2.把类的构造器私有化
    3.定义一个静态成员变量用于存储一个对象
    4.定义一个方法返回单例对象
 (2) 懒汉单例设计模式：



 */
public class SingleInstanceDemo01 {
    public static void main(String[] args) {
        SingleInstance01 instance = SingleInstance01.getInstance();
    }
}

//饿汉单例
class SingleInstance01{

    private static SingleInstance01 instance = new SingleInstance01();

    private SingleInstance01(){

    }

    public static SingleInstance01 getInstance(){
        return instance;
    }
}
