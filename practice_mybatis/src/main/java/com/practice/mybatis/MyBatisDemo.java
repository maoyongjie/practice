package com.practice.mybatis;

import com.practice.mybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class MyBatisDemo {
    public static void main(String[] args) throws IOException {
        test2();
    }

    private static void test1() throws IOException {
        //1.加载mybatis 的核心配置文件，获取sqlsession
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSessionFactory对象，用它执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.执行sql
        List<User> users = sqlSession.selectList("selectAll");

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
//        List<User> list = new ArrayList<User>();
//        list.add(new User("ceshi1", "1112333", "1837@qq.ocm"));
//        list.add(new User("ceshi2", "1114444", "1834@qq.ocm"));
//        int many = mapper.createMany(list);

        List<Integer> list = new ArrayList<Integer>();
        list.add(6);
        list.add(7);
        list.add(8);
        int many = mapper.updateOne(new UserUpdateVO(1,"1111@qq","maooo"));
        log.warn("插入了 " + many);
        sqlSession.commit();
        List<User> users = mapper.selectAll();
        for (User user : users) {
            System.out.println(user.toString());
        }
        //4.sql释放资源
        sqlSession.close();
    }
}
