package com._接口和抽象类区别;

public interface InterfaceClass {

    String CONSTANT = "S";

    void method1(String s);

    default void method2(String s){
        System.out.println(s);
    }
}
