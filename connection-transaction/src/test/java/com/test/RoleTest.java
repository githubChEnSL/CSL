package com.test;/**
 * Description RoleTest
 * Created by 隔山海 .
 * date on 2020/2/25.
 */

import com.dao.IRoleDao;
import com.dao.IUserDao;
import com.domain.Role;
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
 * @ClassName RoleTest
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/25 23:02
 * @Version 1.0
 */
public class RoleTest {
    private InputStream in;
    private SqlSession session;
    private IRoleDao roleDao;

    /**
     * 初始化
     * @throws Exception 异常
     *   注解Before：用于在测试方法之前执行
     */
    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        roleDao = session.getMapper(IRoleDao.class);
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
     * 测试findAll
     */
    @Test
    public void findAllTest(){
        List<Role> roles=roleDao.findAll();
        for (Role role:roles
             ) {
            System.out.println(role);
        }
    }
}
