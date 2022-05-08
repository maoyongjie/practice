package com._泛型的概念.自定义泛型类;

/*
    泛型类的格式：
        修饰符 class 类型<泛型变量>{
        }
        泛型变量建议使用E T K V

 */

import java.util.ArrayList;

public class GenericDemo {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        ArrayList<String> arrayList = new ArrayList<>();
        Integer[] name = {1,2,3};
        Integer integer = MyArrayList.arrToString(name);
    }
}

class MyArrayList<E>{

    private ArrayList<E> list = new ArrayList<>();

    public void add(E e){};

    public void remove(E e){};

    public static <E> E arrToString(E[] nums){
        return nums[0];
    }

    public E get(E[] nums){
        return nums[0];
    }

}

