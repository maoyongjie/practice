package com.practice.datastructure.designpatterns.代理模式;

/**
 * @author MaoYongjie
 * @date 2022/8/2 16:57
 * @Description:
 */

/**
 * JDK 动态代理有一个最致命的问题是其只能代理实现了接口的类
 * CGLIB 通过继承方式实现代理
 */
public class ZuhuImpl implements Zuhu {
    @Override
    public void zuFang() {
        System.out.println("租户租房了");
    }
}
