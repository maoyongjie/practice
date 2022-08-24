package com.practice.springboot.service;

/**
 * @author MaoYongjie
 * @date 2022/8/24 13:49
 * @Description:
 */
public class MessageServiceImpl implements MessageService{

    @Override
    public String getMessage() {
        return "hello world";
    }
}
