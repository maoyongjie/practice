package com.practice.mybatis.mapper;

import com.practice.mybatis.User;

import java.util.List;

public interface UserMapper {
    List<User> selectAll();
}
