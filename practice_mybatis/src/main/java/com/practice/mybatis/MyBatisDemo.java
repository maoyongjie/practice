package com.practice.mybatis;

import com.github.pagehelper.PageHelper;
import com.practice.mybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MyBatisDemo {
    public static void main(String[] args) throws IOException {
        insertData();
//        testLimitPage();
//        testRowBoundsPage();
//        testPageHelper();
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

    private static void testLimitPage() throws IOException {
        SqlSession sqlSession = getSqlsession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startIndex", 2);
        map.put("pageSize", 2);
        List<User> list = mapper.selectLimit(map);
        for (User user : list) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    private static void testRowBoundsPage() throws IOException {
        SqlSession sqlsession = getSqlsession();
        List<User> users = sqlsession.selectList("com.practice.mybatis.mapper.UserMapper.rowBounds", null, new RowBounds(2, 2));
        for (User user : users) {
            System.out.println(user);
        }
        sqlsession.close();
    }

    private static void testPageHelper() throws IOException {
        SqlSession sqlsession = getSqlsession();
        PageHelper.startPage(2,2);
        UserMapper mapper = sqlsession.getMapper(UserMapper.class);
        List<User> users = mapper.selectUsers();
        for (User user : users) {
            System.out.println(user);
        }
        sqlsession.close();
    }

    private static SqlSession getSqlsession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSessionFactory对象，用它执行sql
        return sqlSessionFactory.openSession();
    }

    private static void insertData() throws IOException {
        SqlSession sqlSession = getSqlsession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = new ArrayList<User>();
        list.add(new User("ceshi1", "1112333", "1837@qq.ocm"));
        list.add(new User("ceshi2", "111fsihe44", "18er34@qq.ocm"));
        list.add(new User("ceshi3", "11vsd444", "183t4@qq.ocm"));
        list.add(new User("ceshi4", "1114svd4", "183re4@qq.ocm"));
        mapper.createMany(list);
        sqlSession.commit();
        sqlSession.close();
    }
}
