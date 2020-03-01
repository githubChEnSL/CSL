package com;/**
 * Description AnnoTest
 * Created by 隔山海 .
 * date on 2020/3/1.
 */

import com.dao.IUserDao;
import com.domain.User;
import org.apache.ibatis.annotations.Insert;
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
 * @ClassName AnnoTest
 * @Description TODO
 * @Author 隔山海
 * @date 2020/3/1 11:11
 * @Version 1.0
 */
public class AnnoTest {
    private InputStream in;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        session = new SqlSessionFactoryBuilder().build(in).openSession();
        userDao = session.getMapper(IUserDao.class);
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
        List<User> users = userDao.findAll();
        for (User user: users
             ) {
            System.out.println(user);
        }
    }
    /**
     * 测试添加用户
     */
    @Test
    public void TestSaveUser() throws Exception{
        User ur= new User();
        ur.setUserName("哈哈");
        userDao.saveUser(ur);
        List<User> users =userDao.findAll();
        for (User user: users
        ) {
            System.out.println(user);
        }
    }
    /**
     * 测试修改用户
     */
    @Test
    public void TestUpdateUser() throws Exception{
        User ur= new User();
        ur.setUserId(119);
        ur.setUserName("哈哈1");
        userDao.updateUser(ur);
        List<User> users =userDao.findAll();
        for (User user: users
        ) {
            System.out.println(user);
        }
    }

    /**
     * 测试删除用户
     */
    @Test
    public void TestDeleteUser() throws Exception{
        int result=userDao.deleteUser(121);
        if (result==1){
            System.out.println("删除成功！");
        }else{
            System.out.println("删除失败！");
        }
        List<User> users =userDao.findAll();
        for (User user: users
        ) {
            System.out.println(user);
        }
    }

    /**
     * 测试根据用户编号获取用户
     */
    @Test
    public void TestFindByUserId() throws Exception{
        User user =userDao.findById(1);
        System.out.println(user);
    }

    /**
     * 测试根据用户名称获取用户
     */
    @Test
    public void TestFindByUserName() throws Exception{
        List<User> users =userDao.findUserByName("哈哈");
        for (User user: users
             ) {
            System.out.println(user);
        }
    }

    /**
     * 测试查询用户总数
     */
    @Test
    public void TestFindTotal() throws Exception{
        int Total= userDao.findTotal();
        System.out.println(Total);
    }
}
