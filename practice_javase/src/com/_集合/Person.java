package com._集合;



import java.util.*;
import java.util.stream.Collectors;


public class Person {
    private String name;
    private String phoneNumber;

    public Person() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Person(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

//    @Test
//    public void test1() {
//        TreeMap<Person, String> treeMap = new TreeMap<>((person1, person2) ->
//                Integer.compare(0, person1.getAge() - person2.getAge()));
//        treeMap.put(new Person(3), "person1");
//        treeMap.put(new Person(18), "person2");
//        treeMap.put(new Person(35), "person3");
//        treeMap.put(new Person(16), "person4");
//        treeMap.entrySet().stream().forEach(personStringEntry -> {
//            System.out.println(personStringEntry.getValue());
//        });
//        System.out.println(treeMap.hashCode());
//        TreeMap<Person, String> treeMap2 = treeMap;
//        System.out.println(treeMap2.hashCode());
//    }


    public static void main(String[] args) {
        List<Person> bookList = new ArrayList<>();
        bookList.add(new Person("jack","18163138123"));
        bookList.add(new Person("martin","111"));
// 空指针异常
        Map<String, String> collect = bookList.stream().collect(Collectors.toMap(Person::getName, Person::getPhoneNumber));
        System.out.println(collect);

    }
}

