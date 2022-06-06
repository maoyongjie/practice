package com._方法引用;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.IntFunction;

/**
    _方法引用：可以进一步简化lambda表达式写法，关键语法是::
    四种形式：
    1.静态方法的引用
    2.实例方法的引用
    3.特定类型方法的引用
    4.构造器的引用
        格式::类名::new
        注意点：前后参数一致的情况下，又在创建对象就可以使用构造器引用

 */

public class MethodDemo01 {
    public static void main(String[] args) {
        List<String> lists = new ArrayList<>();
        lists.add("java1");
        lists.add("java2");
        lists.add("java3");

        lists.forEach(s -> System.out.println(s));

        //_方法引用
        lists.forEach(System.out::println);
        Student s1 = new Student("mao",25);
        Student s2 = new Student("mao",21);
        Student s3 = new Student("mao",22);
        List<Student> students = Arrays.asList(s1, s2, s3);
        //使用静态方法进行简化
        Collections.sort(students,((o1, o2) -> Student.compareByAge(o1,o2)));
        //如果前后参数是一样的，而且方法是静态方法，即可以使用静态方法引用
        students.sort(Student::compareByAge);

        //实例方法简化  使用实例化对象名::方法名 只有一个参数且相同
        lists.forEach(System.out::println);

        //构造方法简化
//        String[] objects = lists.toArray(String[]::new);

    }
}
