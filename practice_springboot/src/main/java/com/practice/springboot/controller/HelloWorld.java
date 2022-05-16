package com.practice.springboot.controller;

import com.practice.springboot.service.Student;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author MaoYongjie
 * @date 2022/3/9 9:15
 * @Description:
 */
@RestController
@RequestMapping("/hello")
@EnableAutoConfiguration
public class HelloWorld {

    @Autowired
    Student student;

    @GetMapping("/y")
    public String helloWorld1(){
        student.setName("2");
        student.printName();
        return "Hello World!";
    }

    @GetMapping("/e")
    public String helloWorld2(){

        student.printName();
        return "Hello World!";
    }

}
