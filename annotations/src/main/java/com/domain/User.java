package com.domain;/**
 * Description User
 * Created by 隔山海 .
 * date on 2020/3/1.
 */

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName User
 * @Description TODO
 * @Author 隔山海
 * @date 2020/3/1 10:57
 * @Version 1.0
 */
public class User implements Serializable {
    private Integer userId;
    private String userName;
    private List<Account> accounts;

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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
