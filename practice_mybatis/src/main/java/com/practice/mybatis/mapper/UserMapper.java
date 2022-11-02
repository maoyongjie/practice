package com.practice.mybatis.mapper;

import com.practice.mybatis.User;
import com.practice.mybatis.UserUpdateVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    List<User> selectAll();

    int createOne(User user);

    int createMany(List<User> users);

    int deleteOne(Integer id);

    int deleteMany(List<Integer> id);

    int updateOne(UserUpdateVO vo);

    int updateMany(List<UserUpdateVO> vo);
}
