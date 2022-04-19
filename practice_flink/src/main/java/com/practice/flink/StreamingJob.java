package com.practice.flink;

import org.apache.flink.shaded.guava18.com.google.common.base.Joiner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author MaoYongjie
 * @date 2022/3/9 18:52
 * @Description:
 */
public class StreamingJob {
    public static void main(String[] args) {
        String[] strings = {"1","2","3","4"};
        String join = Joiner.on(',').join(strings);
        System.out.println(join);
        Set set = new HashSet<>(Arrays.asList("One", "Two", "Three", "Four", "Five", "Six"));

        System.out.println("Set = " + set);

        String str = String.join(",", set);

        System.out.println("Comma separated String: "+ str);
    }
}
