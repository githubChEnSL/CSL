package com.mvueproject.mvueproject.service;

import com.mvueproject.mvueproject.pojo.User;
import com.mvueproject.mvueproject.util.Page;

import java.util.List;

/**
 * @author 隔山海
 * @date 2020/2/10
 */
public interface UserService {
    /**
     * 查询用户列表
     * @return 返回用户列表
     */
    List<User> userList();

    /**
     * 根据用户编号获取用户信息
     * @param id 用户编号
     * @return 返回用户信息
     */
    User getById(String id);

    /**
     * 保存用户
     * @param user 用户信息
     */
    void save(User user);

    /**
     * 根据用户Id删除用户信息
     * @param id 用户ID
     */
    void deleteById(String id);

    /**
     * 根据Id更新用户信息
     * @param user 用户ID
     */
    void updateById(User user);

    /**
     * 根据page查询
     * @param page page
     * @return 返回查询结果
     */
    Page<User> getByPage(Page<User> page);
}
