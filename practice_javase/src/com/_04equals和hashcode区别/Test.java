package com._04equals和hashcode区别;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test {
    public static void main(String[] args) {
        User user = new User("zhangsan",5);
        User user2 = new User("zhangsan",5);
        Map<User,String> map = new HashMap<>();
        map.put(user,"1");
        List<String> list = new CopyOnWriteArrayList<>();
        System.out.println(map.get(user2));
    }


}
