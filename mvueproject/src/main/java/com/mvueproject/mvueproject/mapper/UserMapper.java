package com.mvueproject.mvueproject.mapper;

import com.mvueproject.mvueproject.pojo.User;
import com.mvueproject.mvueproject.util.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 隔山海
 * @date 2020/2/10
 */
@Mapper
@Component
public interface UserMapper {

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
     * 根据用户ID删除用户信息
     * @param id 用户ID
     */
    void deleteById(String id);

    /**
     * 更新信息
     * @param user 用户信息
     */
    void updateById(User user);

    /**
     * 分页查询
     * @param page page
     * @return 获取分页查询结果
     */
    List<User> getListByPage(Page<User> page);

    /**
     * 查询总数
     * @param page page
     * @return 获取总数
     */
    int getCountByPage(Page<User> page);

    /**
     * 查询所有数目
     * @return 返回所有数目
     */
    int getCount();
}
