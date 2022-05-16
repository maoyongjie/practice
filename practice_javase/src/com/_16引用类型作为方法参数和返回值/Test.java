package com._16引用类型作为方法参数和返回值;

/**
 * @author MaoYongjie
 * @date 2022/4/26 15:45
 * @Description:
 */
public class Test {
    private String name;

    public Test(String name) {
        System.out.println("有参构造器");
        this.name = name;
    }

    public Test() {
        System.out.println("无参构造器");
    }

    public static void main(String[] args) {
        Test test;
    }
}
