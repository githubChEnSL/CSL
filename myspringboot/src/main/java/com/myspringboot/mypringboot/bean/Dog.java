package com.myspringboot.mypringboot.bean;/**
 * Description
 * Created by 隔山海 .
 * date on 2020/3/7.
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName Dog
 * @Description TODO
 * @Author 隔山海
 * @date 2020/3/7 13:39
 * @Version 1.0
 */
public class Dog {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
