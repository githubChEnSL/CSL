package com.myspringboot.mypringboot.controller;/**
 * Description HelloController
 * Created by 隔山海 .
 * date on 2020/3/8.
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HellowController
 * @Description TODO
 * @Author 隔山海
 * @date 2020/3/8 21:24
 * @Version 1.0
 */

@RestController
public class HelloController {

    @Value("${person.last-name}")
    public String name;

    @RequestMapping("/hello")
    public String sayHello(){
        return "hello"+this.name;
    }
}
