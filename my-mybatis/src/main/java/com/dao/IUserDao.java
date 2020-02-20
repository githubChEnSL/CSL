package com.dao;

import com.domain.User;
import com.mybatis.annotation.Select;

import java.util.List;

/**
 * @author 隔山海
 * @data:2020/1/16
 * @description:用户持久层
 */
public interface IUserDao {

    /**
     * 获取所有用户信息
     * @return 返回所有的用户信息
     */
    @Select(value = "select * from user")
    List<User> findAll();
}
