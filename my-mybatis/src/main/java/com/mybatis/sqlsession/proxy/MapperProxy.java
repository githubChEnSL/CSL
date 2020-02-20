package com.mybatis.sqlsession.proxy;/**
 * Description MapperProxy
 * Created by 隔山海 .
 * date on 2020/2/18.
 */

import com.mybatis.cfg.Configuration;
import com.mybatis.cfg.Mapper;
import com.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MapperProxy
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/18 22:32
 * @Version 1.0
 */
public class MapperProxy implements InvocationHandler {

//    /**
//     * map的key是全限定类名+方法名
//     */
    private Map<String, Mapper> mappers = new HashMap<String, Mapper>();

    private Connection conn;

    public MapperProxy(Map<String, Mapper> mappers,Connection conn){
        this.mappers=mappers;
        this.conn=conn;
    }

    /**
     * 用于对方法进行增强的，我们的增强其实就是调用select方法
     * @param proxy proxy
     * @param method method
     * @param args args
     * @return return
     * @throws Throwable Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        获取方法名
        String methodName=method.getName();
//        获取方法所在类的名称
        String className=method.getDeclaringClass().getName();
//        组合key
        String key = className+"."+methodName;
//        获取mappers中的Mapper对象
        Mapper mapper = mappers.get(key);
//        判断是否有mapper
        if(mapper == null){
            throw new IllegalArgumentException("传入的参数有误");
        }
//        调用工具类，执行函数
        return new Executor().selectList(mapper,conn);
    }
}
