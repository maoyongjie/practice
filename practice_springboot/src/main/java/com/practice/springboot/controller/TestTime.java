package com.practice.springboot.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

/**
 * @author MaoYongjie
 * @date 2022/3/21 9:59
 * @Description:
 */
public class TestTime {
    public static void main(String[] args) {
        System.out.println(DateUtil.date(1646064000000L));

//        String now = DateUtil.format(DateUtil.date(), DatePattern.PURE_DATETIME_PATTERN);
//        System.out.println(now);
//
//        System.out.println(DateUtil.beginOfWeek(DateUtil.date()).getTime());
//        System.out.println(DateUtil.endOfWeek(DateUtil.date()).getTime());
    }
}
