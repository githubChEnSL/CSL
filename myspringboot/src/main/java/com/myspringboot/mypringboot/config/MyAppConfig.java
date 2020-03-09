package com.myspringboot.mypringboot.config;/**
 * Description MyAppConfig
 * Created by 隔山海 .
 * date on 2020/3/8.
 */

import com.myspringboot.mypringboot.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyAppConfig
 * @Description TODO
 * @Author 隔山海
 * @date 2020/3/8 23:13
 * @Version 1.0
 *  * 指明当前类为配置类；就是来替代之前的Spring配置文件
 *  * 在配置文件中用<bean id="" class=""><bean/>添加组件
 */
@Configuration
public class MyAppConfig {

    /**
     * 将方法的返回值添加到容器中；容器中这个组件默认的id就是方法名
     * @return 将方法返回
     */
    @Bean
    public HelloService helloService(){
        System.out.println("配置类@Bean给容器中添加组件");
        return new HelloService();
    }
}
