package com.practice.mybatis;

import com.practice.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class MyBatisDemo {
    public static void main(String[] args) throws IOException {
        test2();
    }

    private void test1() throws IOException {
        //1.加载mybatis 的核心配置文件，获取sqlsession
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSessionFactory对象，用它执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.执行sql
        List<User> users = sqlSession.selectList("test.selectAll");

        for (User user : users) {
            System.out.println(user.toString());
        }

        //4.sql释放资源
        sqlSession.close();
    }

    //代理开发
    private static void test2() throws IOException {
        //1.加载mybatis 的核心配置文件，获取sqlsession
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSessionFactory对象，用它执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.selectAll();
        for (User user : users) {
            System.out.println(user.toString());
        }

        //4.sql释放资源
        sqlSession.close();
    }
}
