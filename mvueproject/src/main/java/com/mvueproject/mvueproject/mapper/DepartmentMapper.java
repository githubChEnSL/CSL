package com.mvueproject.mvueproject.mapper;

import com.mvueproject.mvueproject.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @authon:隔山海
 * @data:2020/2/10
 * @description:
 */
@Mapper
@Component
public interface DepartmentMapper {
    /**
     * 查询部门列表
     * @return 返回部门列表
     */
    List<Department> departmentList();
}
