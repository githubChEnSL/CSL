package com.mybatis.utils;/**
 * Description DataSourceUtil
 * Created by 隔山海 .
 * date on 2020/2/18.
 */

import com.mybatis.cfg.Configuration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @ClassName DataSourceUtil
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/18 23:24
 * @Version 1.0
 * 创建数据源的工具类
 */
public class DataSourceUtil {

    /**
     * 用于获取一个连接
     * @param cfg 获取一个数据源
     * @return 返回一个数据源
     */
    public static Connection getConnection(Configuration cfg) {
        try {
            Class.forName(cfg.getDriver());
           return DriverManager.getConnection(cfg.getUrl(),cfg.getUsername(),cfg.getPassword());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
