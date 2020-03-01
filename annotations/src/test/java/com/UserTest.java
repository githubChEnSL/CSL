package com;/**
 * Description UserDao
 * Created by 隔山海 .
 * date on 2020/3/1.
 */

import com.dao.IUserDao;
import com.domain.User;
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
 * @ClassName UserTest
 * @Description TODO
 * @Author 隔山海
 * @date 2020/3/1 23:11
 * @Version 1.0
 */
public class UserTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void init() throws Exception{
        System.err.println("测试开始！");
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory =  new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }

    /**
     *
     * @throws Exception
     */
    @After
    public void destroy() throws Exception{
//        session.commit();
//        session.close();
        in.close();
        System.err.println("测试结束！");
    }

    /**
     * 测试一级缓存
     * @throws Exception 异常
     */
    @Test
    public void TestFindById() throws Exception{
        User user=userDao.findById(2);
        System.out.println(user);

        User user2 = userDao.findById(2);
        System.out.println(user2);

        System.err.println(user==user2);
    }

    /**
     * 测试二级缓存
     * @throws Exception 异常
     */
    @Test
    public void SecondLeaveCacheTest() throws Exception{
        User user=userDao.findById(2);
        System.out.println(user);
        session.close();//释放一级缓存

        SqlSession session1 = factory.openSession();//再次打开session
        IUserDao dao1 = session1.getMapper(IUserDao.class);
        User user1 = dao1.findById(2);
        System.out.println(user1);
        System.err.println(user==user1);
        session1.close();
    }



    /**
     * 测试一对多
     * @throws Exception 异常
     */
    @Test
    public void TestUser() throws Exception{
        List<User> users=userDao.findAll();
        for (User user :
                users) {
            System.out.println("--------------------");
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }
}
