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

        System.out.println(DateUtil.parse("20220522130000", DatePattern.PURE_DATETIME_PATTERN).getTime());

        System.out.println(DateUtil.parse("20220522135959", DatePattern.PURE_DATETIME_PATTERN).getTime());


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
        int a =89296256+68148158+65163899+63071333+54785358+17603241+14498587+43027030+69416582+69658306+69497053+69067104
                +62489054+61803982+61058225+63879068+63117630+65596684+59645093+20797975+15996693+39092718+69587026+69782189;
        System.out.println(a);

    }
}
