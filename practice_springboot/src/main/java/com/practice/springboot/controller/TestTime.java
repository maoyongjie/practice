package com.practice.springboot.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;

import java.util.*;

/**
 * @author MaoYongjie
 * @date 2022/3/21 9:59
 * @Description:
 */
public class TestTime {
    public static void main(String[] args) {
//        String tabIdList = "1,2,3,4,5";
//        List<String> list1 = Arrays.asList(tabIdList.split(","));
//
//        ArrayList<String> list = new ArrayList<>(list1);
//        list.remove("3");
//
//        System.out.println(DateUtil.date(1641360658258L));
//
//        String now = DateUtil.format(DateUtil.date(), DatePattern.PURE_DATETIME_PATTERN);
//        System.out.println(now);

        System.out.println(DateUtil.parse("20220510130000", DatePattern.PURE_DATETIME_PATTERN).getTime());

        System.out.println(DateUtil.offset(DateUtil.date(), DateField.DAY_OF_MONTH, 1));
        System.out.println(DateUtil.parse("20220510140000", DatePattern.PURE_DATETIME_PATTERN).getTime());


//        DateTime time = DateUtil.offsetMonth(DateUtil.parse(date, DatePattern.PURE_DATETIME_PATTERN), 1);
//        System.out.println(DateUtil.format(time, DatePattern.PURE_DATETIME_PATTERN));
////        System.out.println(DateUtil.beginOfWeek(DateUtil.date()).getTime());
////        System.out.println(DateUtil.endOfWeek(DateUtil.date()).getTime());
//
//        String topic = "gvp.ods.Motor";
//        String replace = topic.replace("ods", "dwd");
//        System.out.println(topic);
//        System.out.println(replace);
        Map<String,Object> map = new HashMap<>();
        map.put("ss",null);
        System.out.println(ObjectUtil.isNull(map.get("ss")));

    }
}
