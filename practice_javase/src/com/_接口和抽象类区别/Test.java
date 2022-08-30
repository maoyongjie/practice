package com._接口和抽象类区别;

public class Test extends AbstractClass{
    public static void main(String[] args) {
        (new Test()).method1();
    }

    private String CONSTANT = "2";

    @Override
    void print(String s) {
           method1();
    }
}
