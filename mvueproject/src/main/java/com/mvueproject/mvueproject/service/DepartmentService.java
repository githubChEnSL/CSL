package com.mvueproject.mvueproject.service;

import com.mvueproject.mvueproject.pojo.Department;

import java.util.List;

/**
 * @author 隔山海
 * @date 2020/2/10
 */
public interface DepartmentService {

    /**
     * 查询部门列表
     * @return  返回部门列表
     */
    List<Department> departmentList();
}
