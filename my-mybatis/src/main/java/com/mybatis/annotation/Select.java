package com.mybatis.annotation;

import javax.xml.bind.Element;
import java.lang.annotation.*;

/**
 * Description Select
 * Created by 隔山海 .
 * date on 2020/2/20.
 * 查询的注解
 * Retention:注解的生命周期
 *Target:出现的位置
 * @author 隔山海
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Select {

    /**
     * 用于配置Sql语句
     * @return 返回一个Sql语句
     */
    String value();
}
