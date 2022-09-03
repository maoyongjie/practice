package com._JVM;

/**
 * @author MaoYongjie
 * @date 2022/9/3 14:54
 * @Description:
 */
public class TestClassLoader {

    public static void main(String[] args) throws Exception{

        MyClassLoader mcl = new MyClassLoader();
        Class<?> clazz = Class.forName("com._JVM.People", true, mcl);
        Object obj = clazz.newInstance();
        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());
    }
}
