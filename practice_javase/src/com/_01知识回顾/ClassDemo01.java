package com._01知识回顾;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassDemo01 {

    static {
        System.out.println("helloword");
    }

    public static void main(String[] args) {
        List<String> list =  new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.remove("2");
        System.out.println(list);
    }
}
