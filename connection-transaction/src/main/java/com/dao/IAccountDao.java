package com.dao;/**
 * Description IAccountDao
 * Created by 隔山海 .
 * date on 2020/2/25.
 */

import com.domain.Account;
import com.domain.AccountUser;

import java.util.List;

/**
 * @ClassName IAccountDao
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/25 16:13
 * @Version 1.0
 */
public interface IAccountDao {

    /**
     * 查询所有账单,同时获取该账单的所属用户
     * @return 订单信息
     */
    List<Account> findAll();

    /**
     *查询所有账单信息，同时带有用户名
     * @return 查询所有账单信息，同时带有用户名
     */
    List<AccountUser> finAllAccount();

    /**
     * 查询所有账单信息，同时带有用户名
     * 定义封装Account和User的resultMap
     * @return  查询所有账单信息，同时带有用户名
     */
    List<Account> findAllAccounts();
}
