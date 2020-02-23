package com.dao;/**
 * Description dao.IUserDao
 * Created by 隔山海 .
 * date on 2020/2/20.
 */

import com.domain.QueryVo;
import com.domain.User;

import java.util.List;

/**
 * @ClassName IUserDao
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/20 23:59
 * @Version 1.0
 */
public interface IUserDao {
    /**
     * 获取所有的的User
     * @return 返回UserList
     */
    List<User> findAll();

    /**
     * 保存用户
     * @param user 用户信息
     */
    void saveUser(User user);

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return 返回1表示执行成功，0表示执行失败
     */
    int updateUserById(User user);

    /**
     * 根据用户Id删除用户
     * @param Id 用户编号
     * @return 返回1表示执行成功，0表示执行失败
     */
    int deleteUserById(Integer Id);

    /**
     * 通过用户Id获取用户信息
     * @param Id 用户编号
     * @return 用户信息
     */
    User findUserById(Integer Id);

    /**
     * 根据用户名称模糊查询用户信息
     * @param username 用户名称
     * @return 返回用户信息
     */
    List<User> findByName(String username);

    /**
     * 查询总用户数
     * @return 返回总用户数
     */
    int findTotal();

    /**
     * 条件查询
     * @param vo vo
     * @return 用户信息
     */
    List<User> findUserByVo(QueryVo vo);
}
