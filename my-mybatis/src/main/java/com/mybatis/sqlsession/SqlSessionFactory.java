package com.mybatis.sqlsession;/**
 * Description SqlSessionFactory
 * Created by 隔山海 .
 * date on 2020/2/18.
 */

/**
 * @ClassName SqlSessionFactory
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/18 13:21
 * @Version 1.0
 */
public interface SqlSessionFactory {

    /**
     * 打开一个Session对象
     * @return 返回一个Session
     */
    SqlSession openSession();
}
