package com.practice.datastructure.designpatterns.代理模式;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author MaoYongjie
 * @date 2022/8/2 16:59
 * @Description:
 */
public class ZhongJie implements InvocationHandler {

    /**
     * 代理类中的真实对象
     */
    private final Object target;

    public ZhongJie(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("中介找房源");
        Object res = method.invoke(target, args);
        System.out.println("中介收中介费");
        return res;
    }
}
