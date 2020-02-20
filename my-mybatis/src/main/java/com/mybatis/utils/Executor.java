package com.mybatis.utils;/**
 * Description Executor
 * Created by 隔山海 .
 * date on 2020/2/18.
 */

import com.mybatis.cfg.Mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Executor
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/18 22:48
 * @Version 1.0
 */
public class Executor {
    public <E> List<E> selectList(Mapper mapper, Connection conn){
        PreparedStatement pstm = null;
        ResultSet rs=null;
        try {
//            取出mapper中的数据
            String queryString = mapper.getQueryString();
            String resultType = mapper.getResultType();
            Class domainClass = Class.forName(resultType);
//            获取PreparedStatement对象
            pstm = conn.prepareStatement(queryString);
//            执行sql语句，获取结果集
            rs=pstm.executeQuery();
//            封装结果集
            List<E> list = new ArrayList<E>();
            while(rs.next()){
//                实例化要封装的实体类对象
                E obj = (E) domainClass.newInstance();
//                取出结果集的元信息：ResultSetMetaData
                ResultSetMetaData rsmd=rs.getMetaData();
//                取出总列数
                int columnCount = rsmd.getColumnCount();
//                遍历总列数
                for (int i=1;i<=columnCount;i++){
//                    获取每列的字段名称，列名的序号是从1开始的
                    String columnName = rsmd.getColumnName(i);
//                    根据得到的列名获取每列的值
                    Object columnValue = rs.getObject(columnName);
//                    try {
                        PropertyDescriptor pd = new PropertyDescriptor(columnName,domainClass);
//                    获取他的写入方法
                        Method writeMethod = pd.getWriteMethod();
//                    把获取到列的值，给Object对象赋值
                        writeMethod.invoke(obj,columnValue);
                }
//                将赋值好的对像加到集合中
                list.add(obj);
            }
            return  list;
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally {
            release(pstm,rs);
        }
    }

    private void release(PreparedStatement pstm, ResultSet rs) {
        if (rs!=null){
            try {
                rs.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (pstm != null){
            try{
                pstm.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
