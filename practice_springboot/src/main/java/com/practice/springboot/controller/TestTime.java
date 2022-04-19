package com.practice.springboot.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

/**
 * @author MaoYongjie
 * @date 2022/3/21 9:59
 * @Description:
 */
public class TestTime {
    public static void main(String[] args) {
        System.out.println(DateUtil.date(1641360658258L));

        String now = DateUtil.format(DateUtil.date(), DatePattern.PURE_DATETIME_PATTERN);
        System.out.println(now);
        String date = "20220406150000";
        DateTime parse = DateUtil.parse(date, DatePattern.PURE_DATETIME_PATTERN);
        parse.getTime();

        DateTime time = DateUtil.offsetMonth(DateUtil.parse(date, DatePattern.PURE_DATETIME_PATTERN), 1);
        System.out.println(DateUtil.format(time, DatePattern.PURE_DATETIME_PATTERN));
//        System.out.println(DateUtil.beginOfWeek(DateUtil.date()).getTime());
//        System.out.println(DateUtil.endOfWeek(DateUtil.date()).getTime());

        String topic = "gvp.ods.Motor";
        String replace = topic.replace("ods", "dwd");
        System.out.println(topic);
        System.out.println(replace);


    }
}
