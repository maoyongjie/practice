package com._反射;

/**
 * @author MaoYongjie
 * @date 2022/6/8 19:21
 * @Description:
 */

public class User {

    static {
        System.out.println("静态代码块执行了");
    }

    public String userName;
    public long userId;

    private int age;

    public User() {
    }

    public User(String userName, long userId, int age) {
        this.userName = userName;
        this.userId = userId;
        this.age = age;
    }

    public int getLastAge() {
        return age + 1;
    }

    private void printName() {
        System.out.println(userId + "" + userName);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
