package com.practice.datastructure.dynamicplan;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution651FourKey {
    public static void main(String[] args) {
//        List<S>
        String str = "{}";
        HashMap<String,Object> list = JSON.parseObject(str, HashMap.class);
        System.out.println(list);
    }
}
