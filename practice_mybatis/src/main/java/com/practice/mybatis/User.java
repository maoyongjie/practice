package com.practice.mybatis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Integer id;
    private String userName;
    private String passWord;
    private String email;

    public User(String userName, String passWord, String email) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
    }

    @Override
    public String toString() {
        return "id:"+id+"\nuserName:"+userName+"\npassword:"+passWord+"\nemail:"+email;
    }
}
