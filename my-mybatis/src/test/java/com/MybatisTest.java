package com;
/**
 * Description
 * Created by 隔山海 .
 * date on 2020/2/18.
 */

import com.dao.IUserDao;
import com.domain.User;
import com.mybatis.io.Resources;
import com.mybatis.sqlsession.SqlSession;
import com.mybatis.sqlsession.SqlSessionFactory;
import com.mybatis.sqlsession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName MybatisTest
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/18 1:35
 * @Version 1.0
 */
public class MybatisTest {
    /**
     * 自定义Mybatis
     */
    @Test
    public void TestMyMyBatis(){

        try{
//            读取配置文件
            InputStream in= Resources.getResourceAsStream("SqlMapconfig.xml");

//            创建SqlSessionFactory工厂
            SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
            SqlSessionFactory factory=builder.build(in);
//            使用工厂生产SqlSession对象
            SqlSession session=factory.openSession();
//            使用SqlSession创建Dao接口的代理对象（dao的实现类情况下不需要此操作）
            IUserDao userDao=session.getMapper(IUserDao.class);
//            使用代理对象执行方法
            try{
                 userDao.findAll();
            }catch (Exception e){
                e.printStackTrace();
            }

            List<User> users=userDao.findAll();
            for (User user: users) {
                System.out.println(user.toString());
            }
//            释放资源
            session.close();
            in.close();
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println("TestMybatis出错啦");
        }
    }
}
