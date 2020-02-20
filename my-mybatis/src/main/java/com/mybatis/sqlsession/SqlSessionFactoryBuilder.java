package com.mybatis.sqlsession;
/**
 * Description SqlSessionFactoryBuilder
 * Created by 隔山海 .
 * date on 2020/2/18.
 */

import com.mybatis.cfg.Configuration;
import com.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import com.mybatis.utils.XmlConfigBuilder;

import java.io.InputStream;

/**
 * @ClassName SqlSessionFactoryBuilder
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/18 13:18
 * @Version 1.0
 */
public class SqlSessionFactoryBuilder {

    /**
     * 根据参数的字节输入流构建一个SqlSessionFactory工厂
     * @param config 字节输入流
     * @return 返回一个工厂
     */
    public SqlSessionFactory build(InputStream config) {
        Configuration cfg= XmlConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(cfg);
    }
}
