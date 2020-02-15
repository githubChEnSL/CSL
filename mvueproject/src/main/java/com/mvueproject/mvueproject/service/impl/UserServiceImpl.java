package com.mvueproject.mvueproject.service.impl;

import com.mvueproject.mvueproject.mapper.UserMapper;
import com.mvueproject.mvueproject.pojo.User;
import com.mvueproject.mvueproject.service.UserService;
import com.mvueproject.mvueproject.util.Page;
import com.mvueproject.mvueproject.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author 隔山海
 * @data 2020/2/10 15:38
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询用户列表
     *
     * @return 返回用户列表
     */
    @Override
    public List<User> userList() {
        return userMapper.userList();
    }

    /**
     * 根据用户编号获取用户信息
     *
     * @param id 用户编号
     * @return 返回用户信息
     */
    @Override
    public User getById(String id) {
        return userMapper.getById(id);
    }

    /**
     * 保存用户
     *
     * @param user 用户信息
     */
    @Override
    public void save(User user) {
        Integer Id=userMapper.getCount();
        user.setUserId(Id+1+"");
        user.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        userMapper.save(user);
    }

    /**
     * 根据用户Id删除用户信息
     *
     * @param id 用户ID
     */
    @Override
    public void deleteById(String id) {
        userMapper.deleteById(id);
    }

    /**
     * 根据Id更新用户信息
     * 先查询后更新，主要先查询乐观锁状态。
     * 何为乐观锁：在程序运行中始终认为数据不会修改。
     * 直到更新时才去判断书否已被修改。
     * 如果在查询到更新的这段时间内，数据已经被其他人锁修改，那么这里就不进行被修改
     * 1.A用户：查询得到 version = 1
     * 2.B用户：查询得到 version = 1
     * 3.A用户更新：条件就是 version = 1,更新后 version + 1
     * 4.B用户更新：条件就是 version = 1,没有这条数据、无法更新
     * @param user 用户ID
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateById(User user) {
        User u=userMapper.getById(user.getUserId());
        user.setVersion(u.getVersion());
        user.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        userMapper.updateById(user);
    }

    /**
     * 根据page查询
     *
     * @param page page
     * @return 返回查询结果
     */
    @Override
    public Page<User> getByPage(Page<User> page) {

        Integer pageNumber = page.getPageNumber();
        if(pageNumber == null){
            pageNumber = 1;
            page.setPageNumber(1);
        }
        Integer pageSize = page.getPageSize();
        if(pageSize == null){
            pageSize = 10;
            page.setPageSize(10);
        }
        int index = (pageNumber-1) * pageSize;
        page.setIndex(index);

        handlerSortColumn(page);

        List<User> userList = userMapper.getListByPage(page);
        int count = userMapper.getCountByPage(page);

        page.setList(userList);
        page.setTotalCount(count);
        int totalPage =  (int)Math.ceil(count*1.0/pageSize);
        page.setTotalPage(totalPage);
        return page;
    }

    /**
     * 排序处理
     * @param page page
     */
    private void handlerSortColumn(Page<User> page) {
        //处理排序方式
        String sortMethod=page.getSortMethod();
        if (sortMethod!=null && !"".equals(sortMethod)){
            if (sortMethod.toLowerCase().contains("desc")){
                sortMethod="desc";
            }else{
                sortMethod="asc";
            }
        }
        page.setSortMethod(sortMethod);

        //处理排序方式
        String sortColumn=page.getSortColumn();
        if (sortColumn!=null && !"".equals(sortColumn)){
            sortColumn= StringUtils.upperCharToUnderLine(sortColumn);
            page.setSortColumn(sortColumn);
        }
    }
}
