package com.practice.datastructure.designpatterns.singlebeanpattern2;

/**
 * @author MaoYongjie
 * @date 2022/3/30 19:07
 * @Description: 懒加载保证线程安全
 */

/**
 (2) 懒汉单例设计模式：
    -- 通过类获取单例对象的时候发现没有对象才回去创建一个对象
    -- 实习步骤：
         1.定义一个单例类
         2.把类的构造器私有化
         3.定义一个静态成员变量用于存储一个对象，初始是null
         4.定义一个方法返回单例对象 判断不存在时创建 存在时返回
 */
public class SingleInstanceDemo02 {
    private static SingleInstanceDemo02 instance = null;

    private SingleInstanceDemo02(){
    }

    public static SingleInstanceDemo02 getInstance(){
        if (instance == null) {
            synchronized (SingleInstanceDemo02.class) {
                if (instance == null) {
                    instance = new SingleInstanceDemo02();
                }
            }
        }
        return instance;
    }
}
