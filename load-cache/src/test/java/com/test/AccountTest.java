package com.test;/**
 * Description AccountTest
 * Created by 隔山海 .
 * date on 2020/2/25.
 */

import com.dao.IAccountDao;
import com.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName AccountTest
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/25 20:18
 * @Version 1.0
 */
public class AccountTest {

    private InputStream in;
    private SqlSession session;
    private IAccountDao accountDao;

    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession(true);
        accountDao = session.getMapper(IAccountDao.class);
    }

    /**
     * 释放资源
     * @throws Exception 异常
     * 注解After:用于在测试方法之后执行
     */
    @After
    public void destory() throws Exception{
        session.close();
        in.close();
    }

    /**
     *查询所有账单信息，同时带有用户名
     */
    @Test
    public void finAll() throws Exception{
        List<Account> accounts = accountDao.findAll();
        for (Account account:accounts) {
            System.out.println(accounts);
        }
    }


    @Test
    public void findAllAccounts(){
        List<Account> accounts=accountDao.findAllAccounts();
        for (Account account:accounts) {
            System.out.println(account);
        }
    }

}
