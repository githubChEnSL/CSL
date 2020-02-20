package com.mybatis.sqlsession.defaults;/**
 * Description DefaultSqlSessionFactory
 * Created by 隔山海 .
 * date on 2020/2/18.
 */

import com.mybatis.cfg.Configuration;
import com.mybatis.sqlsession.SqlSession;
import com.mybatis.sqlsession.SqlSessionFactory;

/**
 * @ClassName DefaultSqlSessionFactory
 * @Description SqlSessionFactory接口的实现类
 * @Author 隔山海
 * @date 2020/2/18 22:14
 * @Version 1.0
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration configuration){
        this.cfg=configuration;
    }

    /**
     *用于创建一个新的操作数据库对象
     * @return 创建一个SqlSession
     */
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
