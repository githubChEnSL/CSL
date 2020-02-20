package com.dao.impl;

import com.dao.IUserDao;
import com.domain.User;
import com.mybatis.sqlsession.SqlSession;
import com.mybatis.sqlsession.SqlSessionFactory;

import java.util.List;

/**
 * @author 隔山海
 * @date 2020/1/17
 * @description
 */
public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;
    public UserDaoImpl(SqlSessionFactory factory){
        this.factory=factory;
    }

    /**
     * 此时为编写dao对象的实现类
     * @return 返回所有数据
     */
    public List<User> findAll(){
        //使用工厂串间SqlSession对象
//        SqlSession session=factory.openSession();
//        //使用session执行查询所有方法
//        List<User> users=session.selectList("com.dao.IUserDao.findAll");
//        session.close();
//        return users;
        return null;
    }
}
