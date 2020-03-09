### 1. POM文件

#### 1.父项目

```xml
<parent>
    <artifactId>spring-boot-starter-parent</artifactId>
    <groupId>org.springframework.boot</groupId>
    <version>2.2.5.BUILD-SNAPSHOT </version>
</parent>

他的父项目
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>2.2.5.BUILD-SNAPSHOT</version>
    <relativePath>../../spring-boot-dependencies</relativePath>
  </parent>
用来真正管理Spring Boot应用里的所有依赖版本；
```

Spring Boot的版本仲裁中心；

以后我们导入依赖默认是不需要写版本的；

#### 2.启动器

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

spring-boot-starter-web;

​	spring-boot-starter：spring-boot场景启动器；帮我们导入了WEB模块正常运行所有依赖的组件；

Spring-boot将所有的功能场景都抽取了出来，做成一个个的starters（启动器），只需要在项目里面引入这些starter相关场景的所有依赖都会导入进来；要什么功能就导入什么场景的启动器。

### 2.主程序类，主入口类

```java
/**
 * @SpringBootApplication 标注一个主程序
 */
@SpringBootApplication
public class Main {
    /**
     * 应用启动
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}
```

