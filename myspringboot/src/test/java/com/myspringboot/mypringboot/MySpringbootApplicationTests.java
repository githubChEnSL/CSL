package com.myspringboot.mypringboot;

import com.myspringboot.mypringboot.bean.Person;
import com.sun.javafx.binding.Logging;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * SpringBoot的单元测试；
 * 可以在测试期间很方便的类似编码一样进行自动注入等容器的功能
 */
@SpringBootTest
class MySpringbootApplicationTests {

    @Autowired
    Person person;

    @Autowired
    ApplicationContext ioc;

    @Test
    public void HelloService(){
        boolean b = ioc.containsBean("helloService");
        System.out.println(b);
    }

    @Test
    void contextLoads() {
        System.out.println(person);
    }


//    日志测试


    /**
     * 记录器
     */
    Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    public void TestLog(){
//        System.out.println();
//        日志的级别；由低到高trace<debug<info<warn<error
//        可以调整输出的日志级别：日志就会在这个级别以后的高级别生效
        logger.trace("这是trace....");
        logger.debug("这是debug");
//        springboot默认使用的级别是info,warn,error级别
        logger.info("这个是Info");
        logger.warn("这个是warn");
        logger.error("这个是error");
    }
}
