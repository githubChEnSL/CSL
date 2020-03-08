package com.springboot.controller;/**
 * Description
 * Created by 隔山海 .
 * date on 2020/3/3.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName HellowController
 * @Description TODO
 * @Author 隔山海
 * @date 2020/3/3 14:03
 * @Version 1.0
 */
@Controller
public class HellowController {

    @ResponseBody
    @RequestMapping("/hello")
    public String Hellow(){
        return "Hello？";
    }
}
