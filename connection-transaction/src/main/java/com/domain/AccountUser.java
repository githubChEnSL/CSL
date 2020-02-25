package com.domain;/**
 * Description AccountUser
 * Created by 隔山海 .
 * date on 2020/2/25.
 */

/**
 * @ClassName AccountUser 不常用的多表操作
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/25 21:47
 * @Version 1.0
 */
public class AccountUser extends Account{

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Override
    public String toString() {
        return super.toString()+"AccountUser{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
