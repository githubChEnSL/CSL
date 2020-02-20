package com.domain;

/**
 * @author 隔山海
 * @data:2020/1/16
 * @description:User类
 */
public class User {

    private Integer userId;
    private String userName;

    public Integer getUserid() {
        return userId;
    }

    public void setUserid(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
