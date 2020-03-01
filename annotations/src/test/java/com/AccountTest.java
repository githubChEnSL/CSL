package com;/**
 * Description AccountTest
 * Created by 隔山海 .
 * date on 2020/3/1.
 */

import com.dao.IAccount;
import com.dao.IUserDao;
import com.domain.Account;
import com.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
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
 * @date 2020/3/1 22:56
 * @Version 1.0
 */
public class AccountTest {
    private InputStream in;
    private SqlSession session;
    private IAccount accountDao;

    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        session = new SqlSessionFactoryBuilder().build(in).openSession();
        accountDao = session.getMapper(IAccount.class);
    }

    /**
     *
     * @throws Exception
     */
    @After
    public void destroy() throws Exception{
        session.commit();
        session.close();
        in.close();
    }

    /**
     * 测试基于注解的使用
     */
    @Test
    public void TestFindAll() throws Exception{
        List<Account> accounts = accountDao.findAll();
        for (Account account: accounts
        ) {
            System.out.println("--------------------");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }

    /**
     * 根据用户编号查询账单信息
     */
    @Test
    public void TestFindById() throws Exception{
        List<Account> accounts = accountDao.finById(2);
        for (Account account: accounts
        ) {
            System.out.println(account);
        }
    }
}
