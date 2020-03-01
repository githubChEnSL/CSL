package com.domain;/**
 * Description Account
 * Created by 隔山海 .
 * date on 2020/3/1.
 */

import java.io.Serializable;

/**
 * @ClassName Account
 * @Description TODO
 * @Author 隔山海
 * @date 2020/3/1 22:52
 * @Version 1.0
 */
public class Account implements Serializable {
    private Integer id;
    private Integer userId;
    private Double money;
    private User user;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId=" + userId +
                ", money=" + money +
                '}';
    }
}
