package com._Stream流;

import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
       Stream流的常用api
        forEach: 遍历
        count: 统计
        filter: 过滤
        limit： 取前几个元素
        skip: 跳过前几个
        map: 加工方法
        concat: 合并流

 */

public class StreamDemo01 {
    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
//        map.put("1",null);
//        map.put("2",null);
        map.put("3",1);
        map.put("4",2);
        map.put("5","");
        map.put("6","");
        Map<String, Object> collect = map.keySet().stream().filter(key -> StrUtil.isNotBlank(map.get(key).toString())).collect(Collectors.toMap(key -> key, map::get));
        System.out.println(collect);
    }
}
