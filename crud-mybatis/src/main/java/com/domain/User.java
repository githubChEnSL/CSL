package com.domain;/**
 * Description com.domain.User
 * Created by 隔山海 .
 * date on 2020/2/20.
 */

import java.io.Serializable;

/**
 * @ClassName User
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/20 23:46
 * @Version 1.0
 */
public class User implements Serializable {
    private Integer userId;
    private String userName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
