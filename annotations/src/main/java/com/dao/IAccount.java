package com.dao;/**
 * Description IAccount
 * Created by 隔山海 .
 * date on 2020/3/1.
 */

import com.domain.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @ClassName IAccount
 * @Description TODO
 * @Author 隔山海
 * @date 2020/3/1 22:54
 * @Version 1.0
 */
public interface IAccount {

    /**
     * 查询所有账号
     * @return 返回账号信息，并且获取账户下的所属用户
     */
    @Select("select * from account")
    @Results(id = "accountMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "userid",property = "userId"),
            @Result(column = "money",property = "money"),
            @Result(property = "user",column = "userid",one=@One(select="com.dao.IUserDao.findById",fetchType = FetchType.EAGER))
    })
    List<Account> findAll();

    /**
     * 根据用户编号查询账单
     * @param userId 用户编号
     * @return 返回账单信息
     */
    @Select("select * from account where userid = #{userId}")
//    @ResultMap(value = {"accountMap"})
    List<Account> finById(Integer userId);
}
