package com.mvueproject.mvueproject.controller;

import com.mvueproject.mvueproject.pojo.User;
import com.mvueproject.mvueproject.service.UserService;
import com.mvueproject.mvueproject.util.Page;
import com.mvueproject.mvueproject.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 隔山海
 * @date 2020/2/10
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户列表
     * @return 返回用户列表
     */
    @RequestMapping(value = "/userList",method = RequestMethod.GET)
    public Result userList(){
        List<User> userList = userService.userList();
        return new Result("查询成功！",userList);
    }

    /**
     * 根据用户ID获取用户信息
     * @param id 用户编号
     * @return 返回用户信息
     */
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public Result get(@PathVariable String id){
        User user=userService.getById(id);
        return new Result("查询成功！",user);
    }

    /**
     * 保存用户信息
     * @param user 用户信息
     * @return 返回成功或失败信息
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result save(@RequestBody User user){
       try {
           userService.save(user);
        }catch (Exception e){
           e.printStackTrace();
           return new Result(400,"保存失败！");
       }
        return new Result("保存成功！");
    }

    /**
     * 根据用户ID删除用户信息
     * @param id 用户ID
     * @return 返回成功或失败信息
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id)  {
        try {
            userService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(400,"删除失败");
        }
        return new Result("删除成功");
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result update(@RequestBody User user) {
        try{
            userService.updateById(user);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(400,"更新失败！");
        }
        return new Result("更新成功！");
    }

    /**
     * 分页查询
     */
    @RequestMapping(value = "/page",method = RequestMethod.POST)
    public Result getByPage(@RequestBody Page<User> page){
        page = userService.getByPage(page);
        return new Result("查询成功！",page);
    }
}
