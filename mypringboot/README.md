## 一、使用Spring Initializer 快速创建Spring Boot项目

IDE都支持使用Spring的项目创建向导快速创建一一个Spring Boot项目;
选择我们需要的模块;向导会联网创建Spring Boot项目;
默认生成的Spring Boot项目; 

- 主程序已经生成好了，我们只需要我们自己的逻辑
- 配置文件夹(resources)中的目录结构
  - static：保存所有的静态资源；js css images;WebContent;
  - templates：保存所有模板页面；（Spring Boot默认Jar包使用嵌入式的Tomcat，默认不支持Jsp页面）;可以使用模板引擎（Freemarker,thymeleaf）;
  - application.proeprties:SpringBoot应用的配置文件；

## 二、配置文件

### 1、配置文件

SpringBoot使用一个全局的配置文件,配置文件名是固定的。

- application.properties
- application.yml

配置文件的作用：修改SpringBoot自动配置的默认值；SpringBoot在底层都给我们自动配置好；

YAML ( YAML Ain't Markup Language )
	YAML A Markup Language :是一一个标记语言
	YAML isn't Markup Language :不是一个标记语言;

标记语言:

​	以前的配置文件；大多使用xxx.xml文件；

​	YAML:以数据为中心，比json,xml等更适合做配置文件；

YAML:配置例子

```yaml
server:
  port: 8081
```

XML:

```xml
<server>
	<port>8081</port>
</server>
```

### 2、YAML语法

#### 1.基本语法

k:(空格)v：表示一对键值对（空格必须有）；

以空格的缩进来控制层级关系；只要左对齐的一列数据，都是同一层级的。

``` yaml
server:
  port: 8081
  path: /hello
```

属性和值也是大小写敏感的。

#### 2.值的写法

##### 字面量：普通的值（数字，字符串，布尔）:

​	k: v :字面量直接来写；

​		字符串默认不用加单引号或双引号；

​		“”：双引号->不会转义字符串里的特殊字符；

​			例：“\n” 即输出换行

​		'':单引号->会转义特殊字符

​			例：‘\n’ 即输出 \n

##### 对象、Map（属性和值，键值对）:

​	k: v :在下一行来写对象的属性和值的关系；注意缩进

​		对象还是k:v的方式

``` yaml
	friends:

​			lastName:zhangsan

​			age:20
```

行内写法：

``` yaml
friends{lastName:zhangsan,age:18}
```

##### 数组（List、Set）:

用- 值表示数组中的一个元素

``` yaml
pets:
 - cat
 - dog
 - pig
```

行内写法：

``` yaml
pets: [cat,dog,pig]
```



### 3、配置文件值注入

#### 1.使用@Component+@ConfigurationProperties(prefix = "person")

```java
/**
 * @ClassName Person
 * @Description TODO
 * @Author 隔山海
 * @date 2020/3/7 13:35
 * @Version 1.0
 * * 讲配置文件中的每一个属性的值，映射到这个组件中
 * @ConfigurationProperties:告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定;
 * prefix = "person": 配置文件中哪个下面的所有属性进行一-映射
 * 只有这个组件是容器中的组件，才能容器提供的@Configurat ionProperties功能;
 */
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String lastName;
    private Integer age;
    private Boolean boss;
    private Date birth;

    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;
```

我们可以导入配置文件处理器，以后编辑配置就有提示了

```xml
<!--导入配置文件处理器，配置文件进行绑定就会有提示-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```

配置文件例：

```yaml
person:
  lastName: 嘿嘿
  age: 19
  birth: 2020/08/01
  boss: true
  maps: {k1: v1,k2: 12}
  lists:
    - lisi
    - zhouliu
  dog:
    name: 小狗
    age: 12
```

#### 2.使用Value()获取值

```java
/**
 * <bean class="Person">
 *  <property name="lastname" value="字面量/${key}从环境变量、配置文件中获取值/#{Spring表达式}"></property>
 * </bean>
 */
@Value("${person.last-name}")
private String lastName;
@Value("#{11*2}")
private Integer age;
@Value("true")
private Boolean boss;
```

#### 3.使用@Value和使用@ConfigurationProperties获取值的比较

|                      | @ConfigurationProperties    | @Value                                  |
| -------------------- | --------------------------- | --------------------------------------- |
| 功能                 | 批量注入配置文件的属性      | 一个个属性进行绑定                      |
| 松散绑定（松散语法） | 支持（lastName或last-name） | 不支持(last-name必须和配置文件保持一致) |
| SpEL                 | 不支持                      | 支持                                    |
| JSR303数据校验       | 支持                        | 不支持                                  |
| 复杂类型封装         | 支持                        | 不支持                                  |

配置文件yaml还是properties都能获取到值；

如果说，我们只是在某个业务逻辑中需要获取一下配置文件的某项值，使用@Value；

如果说，我们专门编写了一个javaBean来和配置文件进行映射，我们就直接使用@ConfigurationProperties;



#### 4.配置文件注入值数据校验

```java
@Component
@Validated
public class Person {
    
    @Email
    @Value("${person.last-name}")
    private String lastName;
    
    private Integer age;

    private Boolean boss;
    private Date birth;

    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;
```



#### 5.@PropertySource 与 @ImportResource

@PropertySource：加载指定的配置文件；

```java
@Component
@PropertySource(value = {"classpath:person.properties"})
@ConfigurationProperties(prefix = "person")
public class Person {

    private String lastName;
    private Integer age;
    private Boolean boss;
    private Date birth;
    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;
```

@ImportResource：导入Spring的配置文件，让配置文件里里面的内容生效；

SpringBoot里面没有Spring的配置文件，我们自己编写的配置文件，也不能自动识别；

想让Spring的配置文件生效，加载进来；@ImportResource标注在一个配置类上

```java
@ImportResource(locations = {"classpath:beans.xml"})
导入Spring的配置文件让其生效
```

beans.xml:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="helloService" class="com.myspringboot.mypringboot.service.HelloService"></bean>
</beans>
```



SpringBoot推荐给容器中添加组件的方式：推荐全注解的方式

1.配置类===Spring配置文件

2.只要@Bean给容器中添加组件

```java
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
```

### 4、配置文件占位符

#### 1.随机数

```properties
random. value、{random.int}、 ${random.long}
random. int(10)、{random.int[1024,65536]}
```

#### 2.占位符获取之前配置的值，如果没有可以使用冒号(:)指定默认值

```properties
person.last-name=csl${random.uuid}
person.age=${random.int}
person.birth=2019/01/01
person.boss=false
person.maps.k1=chen
person.maps.k2=chenK2
person.lists=1,2,3,4
person.dog.name=${person.hello:hello}_dog
person.dog.age=12
```

### 5、Profile

#### 1.多Profile文件

我们在主配置文件编写的时候，文件名可以是application-{profile}.properties/yml

默认使用application.properties的配置文件

#### 2.yml支持多文档方式

必须注释掉properties里的相关内容，否则调用的是propeties里的信息；因为properties的优先级高

```yaml
server:
  port: 8089
spring:
  profiles:
    active: prod
---
server:
  port: 8083
spring:
  profiles: dev
---
server:
  port: 8084
spring:
  profiles: prod
```

#### 3.激活指定Profile

 1.在配置文件中指定：

```properties
spring.profiles.active=dev
```

2.命令行：

​	--spring.profiles.active=dev

![1583683770882](C:\Users\隔山海\AppData\Roaming\Typora\typora-user-images\1583683770882.png)

3.虚拟机参数：

 	-Dspring.profiles.active=dev