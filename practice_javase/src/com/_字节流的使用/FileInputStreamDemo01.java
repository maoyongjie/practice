package com._字节流的使用;

public class FileInputStreamDemo01 {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        String s = "1";
        System.out.println(append(s));
    }

    public static String append(String s){
        s +="aaa";
        return s;
    }
}
