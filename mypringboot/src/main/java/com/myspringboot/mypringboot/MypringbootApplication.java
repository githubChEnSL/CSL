package com.myspringboot.mypringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

//@ImportResource(locations = {"classpath:beans.xml"})
@SpringBootApplication
public class MypringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MypringbootApplication.class, args);
    }

}
