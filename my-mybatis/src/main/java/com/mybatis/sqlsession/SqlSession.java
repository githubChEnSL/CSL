package com.mybatis.sqlsession;/**
 * Description SqlSession
 * Created by 隔山海 .
 * date on 2020/2/18.
 */

import com.dao.IUserDao;

/**
 * @ClassName SqlSession
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/18 13:22
 * @Version 1.0
 * 自定义Mybatis中和数据可交互的核心类
 */
public interface SqlSession {

    /**
     * 根据参数创建一个代理对象
     * @param daoInterfaceClass Dao接口字节码
     * @return
     */
    <T> T getMapper(Class<T> daoInterfaceClass);

    /**
     * 释放资源
     */
    void close();
}
