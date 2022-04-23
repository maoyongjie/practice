package com._06继承的概述;

public class ExtendsDemo {
    /*
    子类不能继承父类的构造器
    子类不能继承父类的私有成员和私有方法，但可以通过反射访问
    子类不能继承父类的静态成员，但可以访问

    继承后-成员变量的访问特点
    就近原则

    继承后-构造器的特点
        子类的构造器默认一定会先访问父类的无参构造器，再执行自己的构造器
        子类的构造器首行默认有个super()去调用父类的构造器,并且根据参数选择不同的构造器，
        如果没有对应的参数的构造器，则选择无参构造器

        子类的全属性构造器一定会调用父类的无参构造器，除非通过super(...)指定
     */
}

class Animal {
    String name = "动物名称";

    String age = "动物年龄";

    public Animal() {
        System.out.println("爷爷的无参构造器");
    }

    public Animal(String name) {
        System.out.println("爷爷的name有参构造器");
        this.name = name;
    }

    public Animal(String name, String age) {
        System.out.println("爷爷的所有有参构造器");
        this.name = name;
        this.age = age;
    }
}

class Cat extends Animal {


    public String show(String s) {
        System.out.println(s+"======");
        return s+"======";
    }

    public Cat() {
        System.out.println("爸爸的无参构造器");
    }

    public Cat(String name) {
        super(name);
        System.out.println("爸爸的name有参构造器");
    }

    public Cat(String name,String age){
        super(name);
        System.out.println("爸爸的name&age有参构造器");
    }
}

class YinDuan extends Cat {

    public YinDuan(String age) {
        super();
        this.age = age;
    }

    public YinDuan(String name, String color) {
        super(name,color);
    }


    public String show() {
        System.out.println(name+age);

        return null;
    }

    public static void main(String[] args) {

        YinDuan yinDuan = new YinDuan("xiaomiao","3");

        yinDuan.show();
    }
}
