package com.test;/**
 * Description UserTest
 * Created by 隔山海 .
 * date on 2020/2/29.
 */

import com.dao.IUserDao;
import com.domain.Account;
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
 * @date 2020/2/29 23:30
 * @Version 1.0
 */
public class UserTest {
    private InputStream in;
    private SqlSession session;
    private IUserDao userDao;
    private SqlSessionFactory factory;

    /**
     * 初始化
     * @throws Exception 异常
     *   注解Before：用于在测试方法之前执行
     */
    @Before
    public void init() throws Exception{
//        1.读取配置文件，获取字节码输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
//        2.获取工厂
        factory = new SqlSessionFactoryBuilder().build(in);
//        3.获取session对象
        session = factory.openSession();
//        4.创建dao接口的代理对象
        userDao = session.getMapper(IUserDao.class);
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
    @Test
    public void findAccountByUserId(){
        List<User> users = userDao.findAll();
        for (User user:users
             ) {
            System.out.println(user.toString());
        }
    }

    /**
     * 测试缓存
     */
    @Test
    public void TestCache(){
        SqlSession sqlSession1=factory.openSession();
        IUserDao dao1=sqlSession1.getMapper(IUserDao.class);
        User user1 = dao1.findUserById(1);
        System.out.println(user1);
        sqlSession1.close();//一级缓存消失

        SqlSession sqlSession2=factory.openSession();
        IUserDao dao2=sqlSession2.getMapper(IUserDao.class);
        User user2 = dao2.findUserById(2);
        System.out.println(user2);
        sqlSession2.close();//一级缓存消失

        System.out.println(user1==user2);
    }
}
