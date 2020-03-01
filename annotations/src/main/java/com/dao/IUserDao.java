package com.dao;/**
 * Description IUserDao
 * Created by 隔山海 .
 * date on 2020/3/1.
 */

import com.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @ClassName IUserDao
 * @Description TODO
 * @Author 隔山海
 * @date 2020/3/1 10:59
 * @Version 1.0
 */
@CacheNamespace(blocking = true)
public interface IUserDao {
    /**
     * 查询所有用户
     * @return 返回所有用户信息
     */
    @Select("select * from user")
    @Results(id = "userMap",value = {
            @Result(id=true,column = "userid",property = "userId"),
            @Result(column = "username",property = "userName"),
            @Result(property = "accounts",column = "userid",
                    many = @Many(select = "com.dao.IAccount.finById",
                            fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    /**
     * 添加用户
     * @param user 用户对象
     */
    @Insert("insert into user(username) value(#{userName})")
    void saveUser(User user);

    /**
     * 修改用户信息
     * @param user 修改信息
     */
    @Update("update user set username=#{userName} where userid=#{userId}")
    void updateUser(User user);

    /**
     * 删除用户信息
     * @param userId 用户编号
     * @return 返回1表示删除成功，否则为删除失败
     */
    @Delete("delete from user where userid=#{userId}")
    int deleteUser(Integer userId);

    /**
     * 根据用户编号获取用户信息
     * @param userId 用户编号
     * @return 返回用户信息
     */
    @Select("select * from user where userid=#{userId}")
    @ResultMap(value = {"userMap"})
    User findById(Integer userId);

    /**
     * 根据用户名称查询用户信息
     * @param userName 用户名称
     * @return 返回用户信息
     */
    @Select("select * from user where username like '%${value}%'")
    @ResultMap(value = {"userMap"})
    List<User> findUserByName(String userName);

    /**
     * 查询用户的总数
     * @return 返回总数
     */
    @Select("select count(*) from user")
    int findTotal();
}
