package com._18静态代码块;

/*
  代码块按照有无static修飾可以分為：静态代码块，实例代码块

  （1）静态代码块
            static {

            }
            1.属于类，会与类一起优先加载，而且自动触发执行一次
            2.静态代码块可以用在执行类的方法之前进行字段的初始化操作

   (2) 实例代码块
            {}
            属于类的每个对象的，会与类的每个对象一起加载，每次创建对象的时候，触发一次

 */

import java.util.ArrayList;
import java.util.List;

public class CodeDemo01 {

    public static List<String> cards = new ArrayList<>();

    static {
        System.out.println("静态代码");
        cards.add("红桃3");
    }

    public static void main(String[] args) {
        System.out.println(cards);
    }
}
