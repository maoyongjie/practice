package com._泛型的概念.泛型的通配符;

import java.util.ArrayList;

/*
    E\T\K\V 是在定义泛型时使用
    通配符?是在使用泛型时使用

    泛型的上下限
        ? extend Car : 那么?必须是Car或其子类  上限
        ? super Car : 那么?必须是Car或其父类  下限
 */
public class Demo {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();

    }

    //此处是在使用泛型，而非定义
    public static void run(ArrayList<? extends Car> cars){

    }

}

class Car{

}
class BM extends Car{

}
class BC extends Car{

}