package com.practice.datastructure.designpatterns.代理模式;

import java.lang.reflect.Proxy;

/**
 * @author MaoYongjie
 * @date 2022/8/2 16:58
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        ZuhuImpl zuhu = new ZuhuImpl();
        Zuhu zuhu1 = (Zuhu) Proxy.newProxyInstance(zuhu.getClass().getClassLoader(), zuhu.getClass().getInterfaces(), new ZhongJie(zuhu));
        zuhu1.zuFang();
    }
}
