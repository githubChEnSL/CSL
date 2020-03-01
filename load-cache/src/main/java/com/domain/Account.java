package com.domain;/**
 * Description Account
 * Created by 隔山海 .
 * date on 2020/2/25.
 */

import java.io.Serializable;

/**
 * @ClassName Account
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/25 16:12
 * @Version 1.0
 */
public class Account implements Serializable {
    private Integer id;
    private Integer userid;
    private Double money;

    /**
     * 从表实体应该包含一个主表实体的对象引用
     */
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userid=" + userid +
                ", money=" + money +
                ", user=" + user +
                '}';
    }
}
