package com.dao;/**
 * Description IRoleDao
 * Created by 隔山海 .
 * date on 2020/2/25.
 */

import com.domain.Role;

import java.util.List;

/**
 * @ClassName IRoleDao
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/25 22:55
 * @Version 1.0
 */
public interface IRoleDao {
    /**
     * 查询所有角色
     * @return 返回所有角色信息
     */
    List<Role> findAll();
}
