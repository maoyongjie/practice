package com.practice.springboot.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MaoYongjie
 * @date 2022/3/9 9:15
 * @Description:
 */
@RestController
@RequestMapping("/hello")
@EnableAutoConfiguration
public class HelloWorld {

    @GetMapping("/")
    public String helloWorld(){
        return "Hello World!";
    }
}
