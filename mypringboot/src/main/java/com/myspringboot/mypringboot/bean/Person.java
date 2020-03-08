package com.myspringboot.mypringboot.bean;
/*
 * Description Person
 * Created by 隔山海 .
 * date on 2020/3/7.
 */
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Person
 * @Description TODO
 * @Author 隔山海
 * @date 2020/3/7 13:35
 * @Version 1.0
 * * 讲配置文件中的每一个属性的值，映射到这个组件中
 * @ConfigurationProperties:告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定;默认从全局配置文件中获取值
 * prefix = "person": 配置文件中哪个下面的所有属性进行一-映射
 * 只有这个组件是容器中的组件，才能容器提供的@Configurat ionProperties功能;
 */

@Component
//@PropertySource(value = {"classpath:person.properties"})
@ConfigurationProperties(prefix = "person")
//@Validated
public class Person {

    //获取配置文件值方法二
    /**
     * <bean class="Person">
     *  <property name="lastname" value="字面量/${key}从环境变量、配置文件中获取值/#{Spring表达式}"></property>
     * </bean>
     */

//    @Value("${person.last-name}")
//    获取配置文件值方法三；必须是邮箱格式

//    @Email
//    @Value("${person.last-name}")

    private String lastName;

//    @Value("#{11*2}")

    private Integer age;
    private Boolean boss;
    private Date birth;

//    @Value("${person.maps}")

    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", maps=" + maps +
                ", lists=" + lists +
                ", dog=" + dog +
                '}';
    }
}
