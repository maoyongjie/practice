package com.practice.datastructure.designpatterns.singlebeanpattern2;

/**
 * @author MaoYongjie
 * @date 2022/3/30 19:07
 * @Description: 懒加载保证线程安全
 */
public class Singleton {
    private static Singleton instance = null;

    private Singleton(){
    }

    public static Singleton getInstance(){
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
