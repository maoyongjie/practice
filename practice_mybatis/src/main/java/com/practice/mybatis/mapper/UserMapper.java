package com.practice.mybatis.mapper;

import com.practice.mybatis.User;
import com.practice.mybatis.UserUpdateVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> selectAll();

    int createOne(User user);

    int createMany(List<User> users);

    int deleteOne(Integer id);

    int deleteMany(List<Integer> id);

    int updateOne(UserUpdateVO vo);

    //分页查询
    List<User> selectLimit(Map<String,Object> map);

    //RowBounds分页
    List<User> rowBounds();

    //PageHelper分页
    List<User> selectUsers();
}
