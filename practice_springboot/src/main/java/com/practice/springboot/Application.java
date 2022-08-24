package com.practice.springboot;

import com.practice.springboot.service.MessageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author MaoYongjie
 * @date 2022/3/9 9:13
 * @Description:
 */
//@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
//        SpringApplication.run(Application.class,args);
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        System.out.println("context 启动成功");
        // 从 context 中取出我们的 Bean，而不是用 new MessageServiceImpl() 这种方式
        MessageService messageService = context.getBean(MessageService.class);
        // 这句将输出: hello world
        System.out.println(messageService.getMessage());
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("原来实现CommandLineRunner接口就能运行");
    }
}
