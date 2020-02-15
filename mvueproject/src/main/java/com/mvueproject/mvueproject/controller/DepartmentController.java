package com.mvueproject.mvueproject.controller;

import com.mvueproject.mvueproject.pojo.Department;
import com.mvueproject.mvueproject.service.DepartmentService;
import com.mvueproject.mvueproject.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 隔山海
 * @date 2020/2/10
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询部门列表
     * @return 返回部门列表
     */
    @RequestMapping(value = "/departmentList",method = RequestMethod.GET)
    public Result departmentList(){
        List<Department> departmentList = departmentService.departmentList();
        return  new Result("查询成功！",departmentList);
    }
}
