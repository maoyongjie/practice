package com._17JDK18之后接口新增的方法;

/**
    JDK 1.8开始之后接口新增的三个方法
        (1) 默认方法：相当于实例方法
            ----必须使用default方法修饰
            ----默认会加public修饰
            ----只能用接口的实现类的对象来调用
        (2) 静态方法
            ----可以直接加static修饰
            ----默认会加public修饰
            ----只能用接口的类名称调用

        (3) 私有方法
            ----其实就是私有的实例方法 必须加private修饰,
                通常给其他私有方法和默认方法使用

 */

public class InterfaceDemo implements SportMan{

    public static void main(String[] args) {
        InterfaceDemo interfaceDemo = new InterfaceDemo();
        interfaceDemo.run();
        SportMan.inAddr();
    }

    @Override
    public void talk() {
        System.out.println("说话");
    }
}

interface SportMan{
    default void run(){
        talk();
    }

    static void inAddr(){
        System.out.println("enen");
    }

    void talk();

}
