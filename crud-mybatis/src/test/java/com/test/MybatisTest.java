package com.test;/**
 * Description MybatisTest
 * Created by 隔山海 .
 * date on 2020/2/21.
 */

import com.dao.IUserDao;
import com.domain.QueryVo;
import com.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName MybatisTest
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/21 0:08
 * @Version 1.0
 * 测试Mybatis的CRUD操作类
 */
public class MybatisTest {

    private InputStream in;
    private SqlSession session;
    private IUserDao userDao;

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
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
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

    /**
     * 测试查询所有
     */
    @Test
    public void findAll() throws Exception{
        List<User> userList = userDao.findAll();
        for (User user : userList){
            System.out.println(user);
        }
    }

    /**
     * 测试保存用户
     */
    @Test
    public void saveUser() throws Exception{
        User user = new User();
        user.setUserName("陈？");
        try{
            System.err.println("保存操作之前"+user);
            userDao.saveUser(user);
            //        提交事务
            session.commit();
            System.out.println("添加成功！");
            System.err.println("保存操作之后"+user);
        }catch (Exception e){
            e.printStackTrace();
        }
        findAll();
    }

    /**
     * 测试更新用户
     * @throws Exception
     */
    @Test
    public void updateUser() throws Exception{
        User user = new User();
        user.setUserId(106);
        user.setUserName("杨杨");
        try {
            int result=userDao.updateUserById(user);
            session.commit();
            if (result != 1){
                System.out.println("无此信息，更新失败！");
            }else{
                System.out.println("更新成功！");
                findAll();
            }
        }catch (Exception e){
            System.out.println("更新异常！");
        }
    }

    /**
     * 测试删除用户
     * @throws Exception 异常
     */
    @Test
    public void deleteUser() throws Exception{
        try {
            int i=userDao.deleteUserById(105);
            session.commit();
            if (i != 1){
                System.out.println("无此信息，删除失败！");
            }else{
                System.out.println("删除成功！");
            }
            findAll();
        }catch (Exception e){
            System.out.println("删除异常！");
            findAll();
        }
    }

    /**
     * 测试根据编号获取用户
     * @throws Exception 异常
     */
    @Test
    public void findUserById() throws Exception{
        try {
         User user = userDao.findUserById(106);
         System.out.println(user);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试用户名称模糊查询
     * @throws Exception 异常
     */
    @Test
    public void findByName() throws Exception{
        try {
            List<User> userList = userDao.findByName("%杨%");
            for (User user : userList){
                System.out.println(user);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试查询总用户数
     * @throws Exception
     */
    @Test
    public void findTotal() throws Exception{
        System.out.println(userDao.findTotal());
    }

    /**
     * 测试是哦用QueryVo作为查询条件
     * @throws Exception 异常
     */
    @Test
    public void findByVo() throws Exception{
        User newuser=new User();
        newuser.setUserName("%杨%");
        QueryVo vo=new QueryVo();
        vo.setUser(newuser);
        try {
            List<User> userList = userDao.findUserByVo(vo);
            for (User user : userList){
                System.out.println(user);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
