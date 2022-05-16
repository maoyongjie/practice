package com.practice.springboot.service;

import org.springframework.stereotype.Service;

/**
 * @author MaoYongjie
 * @date 2022/4/26 16:13
 * @Description:
 */
@Service
public class StudentImpl implements Student{

    private String name = "1";

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }
}
