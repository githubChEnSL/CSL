package com.myweb.webdevelopment.controller;/**
 * Description Controller as Hello
 * Created by 隔山海 .
 * date on 2020/3/13.
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName Hello
 * @Description TODO
 * @Author 隔山海
 * @date 2020/3/13 0:34
 * @Version 1.0
 */
@RestController
public class Hello {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "hello me";
    }
}
