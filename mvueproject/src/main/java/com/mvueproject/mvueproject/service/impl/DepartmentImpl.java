package com.mvueproject.mvueproject.service.impl;

import com.mvueproject.mvueproject.mapper.DepartmentMapper;
import com.mvueproject.mvueproject.pojo.Department;
import com.mvueproject.mvueproject.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName DepartmentImpl
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/10 15:38
 * @Version 1.0
 */
@Service
public class DepartmentImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 查询部门列表
     * @return 返回部门列表
     */
    @Override
    public List<Department> departmentList() {
        return departmentMapper.departmentList();
    }
}
