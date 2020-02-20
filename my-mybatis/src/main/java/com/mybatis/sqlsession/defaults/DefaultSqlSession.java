package com.mybatis.sqlsession.defaults;/**
 * Description DefaultSqlSession
 * Created by 隔山海 .
 * date on 2020/2/18.
 */

import com.mybatis.cfg.Configuration;
import com.mybatis.sqlsession.SqlSession;
import com.mybatis.sqlsession.proxy.MapperProxy;
import com.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @ClassName DefaultSqlSession
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/18 22:20
 * @Version 1.0
 * SqlSession接口的实现类
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration cfg;

    private Connection connection;

    public DefaultSqlSession(Configuration configuration){
        this.cfg=configuration;
        this.connection= DataSourceUtil.getConnection(cfg);
    }

    /**
     * 用于创建代理对象
     * @param daoInterfaceClass Dao接口字节码
     * @return
     */
    public <T> T getMapper(Class<T> daoInterfaceClass) {
//        newProxyInstance(代理谁就用谁得类加载器，代理谁就要跟谁有相同得接口，如何代理)
//        new Class[]{daoInterfaceClass}  new一个数组数组，然后将daoInterfaceClass传进去
        return (T)Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),new Class[]{daoInterfaceClass},new MapperProxy(cfg.getMappers(),connection));
    }

    /**
     * 用于释放资源
     */
    public void close() {
        if (connection!=null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
