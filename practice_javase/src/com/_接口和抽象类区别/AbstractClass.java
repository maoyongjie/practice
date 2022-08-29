package com._接口和抽象类区别;

public abstract class AbstractClass {

    private static String CONSTANT = "1";

    abstract void print(String s);

    void method1(){
        System.out.println(CONSTANT);
    }
}
