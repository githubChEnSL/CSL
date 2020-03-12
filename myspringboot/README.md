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

### 6、配置文件加载位置

springboot启动会扫描以下位置的application.properties或者application.yml文件作为Spring boot的默认配置
文件
-file.:./config/
-file./
-classpath:/config/
-classpath:/

优先级由高到低高优先级会覆盖低优先级的配置。

SpringBoot会从这四个位置全部加载主配置文件；互补配置；



*我们还可以通过spring.config.location来改变默认的配置文件位置*
**项目打包好以后.我们可以使用命令行参数的形式,启动项目的时候来指定配置文件的新位置;指定配置文件和默**
**认加载的这些配置文件共同起作用形成互补配置;**



我们还可以通过spring.config.location来改变的配置文件位置

### 7、外部配置加载顺序

**SpringBoot也可以从以下位置加载配置:优先圾从高到低;高优先级的配置覆盖低优先圾的配置.所有的配置会**
**形成互朴配置**
**1.命令行参数**

java -jar xxx.jar --server.port=8087 --server.context-path=/abc

多个配置用空格fen；--配置想=值

**2.来自java:comp/env的JNDI属性**
3.Java系统属性( System.getProperties() )
4.操作系统环境变量
5.RandomValuePropertySource配置的random.*属性值

**由jar包内进行寻找**

**优先加载带profile**

**6.jar包外部的application-{profile}.properties或application.yml(带spring.profile)配置文件**
**7.jar包内部的application-{profile}.properties或application.ym(带spring.profile)配置文件**

**再来加载不带profile**

**8.jar包外部的application.properties或application.ym(不带spring.profile)配置文件**
**9.jar包内部的application.properties或application.yml(不带spring.profile)配置文件**

10.@Configuration注解类.上的@PropertySource
11.通过SpringApplication.setDefaultProperties指定的默认属性

所有zhi配置加载来源 ：

**参考官方文档**

https://docs.spring.io/spring-boot/docs/1.5.9.RELEASE/reference/htmlsingle/#boot-features-external-confg

### 8、自动配置原理

#### 1.自动配置原理

配置文件到底能写什么？怎么写，自动配置原理；

[配置文件能配置的属性参照]: https://docs.spring.io/spring-boot/docs/1.5.9.RELEASE/reference/htmlsingle/#common-application-properties



**自动配置原理：**

1）SpringBoot启动的时候加载主配置类，开启自动配置功能@EnableAutoConfiguration

2）@EnableAutoConfiguration的作用：

- 利用AutoConfigurationImportSelector给容器中导入一些组件。

- 可以查看selectImports()方法的内容；

- List<String> configurations = this.getCandidateConfigurations(annotationMetadata, attributes);获取候选的配置。

  - ```java
    SpringFactoriesLoader.loadFactoryNames()
    扫描所有jar包类路径下的META-INF/spring.factories
    把扫描到的这些文件内容包装成properties对象
    从properties中获取到EnableAutoConfiguration.class类（类名）对应的值，然后把他们添加在容器中
    ```

     

**将类路径下META-INF/spring.factories 里面配置的所有EnableAutoConfiguration的值加入到了容器中;**

```properties
# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,\
org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration,\
org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration,\
org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration,\
org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration,\
org.springframework.boot.autoconfigure.cloud.CloudServiceConnectorsAutoConfiguration,\
org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration,\
org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration,\
org.springframework.boot.autoconfigure.couchbase.CouchbaseAutoConfiguration,\
org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraReactiveDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraReactiveRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseReactiveDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseReactiveRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration,\
org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.elasticsearch.ReactiveElasticsearchRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.elasticsearch.ReactiveRestClientAutoConfiguration,\
org.springframework.boot.autoconfigure.data.jdbc.JdbcRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.ldap.LdapRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.mongo.MongoReactiveRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.neo4j.Neo4jRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.solr.SolrRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration,\
org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration,\
org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration,\
org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration,\
org.springframework.boot.autoconfigure.elasticsearch.jest.JestAutoConfiguration,\
org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientAutoConfiguration,\
org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration,\
org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration,\
org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration,\
org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration,\
org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration,\
org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration,\
org.springframework.boot.autoconfigure.hazelcast.HazelcastJpaDependencyAutoConfiguration,\
org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration,\
org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration,\
org.springframework.boot.autoconfigure.influx.InfluxDbAutoConfiguration,\
org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration,\
org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration,\
org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration,\
org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration,\
org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration,\
org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration,\
org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration,\
org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration,\
org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration,\
org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration,\
org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration,\
org.springframework.boot.autoconfigure.jsonb.JsonbAutoConfiguration,\
org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration,\
org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapAutoConfiguration,\
org.springframework.boot.autoconfigure.ldap.LdapAutoConfiguration,\
org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration,\
org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration,\
org.springframework.boot.autoconfigure.mail.MailSenderValidatorAutoConfiguration,\
org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration,\
org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration,\
org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration,\
org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration,\
org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration,\
org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration,\
org.springframework.boot.autoconfigure.rsocket.RSocketMessagingAutoConfiguration,\
org.springframework.boot.autoconfigure.rsocket.RSocketRequesterAutoConfiguration,\
org.springframework.boot.autoconfigure.rsocket.RSocketServerAutoConfiguration,\
org.springframework.boot.autoconfigure.rsocket.RSocketStrategiesAutoConfiguration,\
org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration,\
org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration,\
org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration,\
org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration,\
org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration,\
org.springframework.boot.autoconfigure.security.rsocket.RSocketSecurityAutoConfiguration,\
org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyAutoConfiguration,\
org.springframework.boot.autoconfigure.sendgrid.SendGridAutoConfiguration,\
org.springframework.boot.autoconfigure.session.SessionAutoConfiguration,\
org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration,\
org.springframework.boot.autoconfigure.security.oauth2.client.reactive.ReactiveOAuth2ClientAutoConfiguration,\
org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration,\
org.springframework.boot.autoconfigure.security.oauth2.resource.reactive.ReactiveOAuth2ResourceServerAutoConfiguration,\
org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration,\
org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration,\
org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration,\
org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration,\
org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration,\
org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration,\
org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration,\
org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration,\
org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.ReactiveWebServerFactoryAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.function.client.ClientHttpConnectorAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration,\
org.springframework.boot.autoconfigure.websocket.reactive.WebSocketReactiveAutoConfiguration,\
org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration,\
org.springframework.boot.autoconfigure.websocket.servlet.WebSocketMessagingAutoConfiguration,\
org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration,\
org.springframework.boot.autoconfigure.webservices.client.WebServiceTemplateAutoConfiguration
```

每一个这样的 xxxAutoConfiguration类都是容器中的一个租一间，都加入到容器中；用他们来做自动配置；

3）每一个自动配置类进行自动配置功能；

4）以**HttpEncodingAutoConfiguration****（HTTP编码自动配置）**为例解释自动配置原理；

```java
//表示这是一个配置类，以前编写的配置文件一样，也可以给容器中添加组件
@Configuration(proxyBeanMethods = false)

//启用指定类的ConfigurationProperties功能；将配置文件中对应的值和HttpProperties绑定起来
//HttpEncodingProperties加入到ioc容器中
@EnableConfigurationProperties({HttpProperties.class})

//Spring底层@Conditional注解（spring注解版）。根据不同的条件，如果满足指定的条件，整个配置类里里面的配置就会生效；判断当前应用是否为WEB应用；如果是，当前配置类就生效
@ConditionalOnWebApplication(type = Type.SERVLET)

//判断当前项目有没有这个类；CharacterEncodingFilter：SpringMVC进行乱码解决的过滤器；
@ConditionalOnClass({CharacterEncodingFilter.class})

//判断配置文件中是否存在某个配置，即spring.http.encoding.enabled;如果不存在，判断也是成立的
//即使我们的配置文件中不配置spring.http.encoding.enabled=true也是默认生效；
@ConditionalOnProperty(prefix = "spring.http.encoding",value = {"enabled"},matchIfMissing= true)

public class HttpEncodingAutoConfiguration {
    
    //他已经和SpringBoot的配置文件映射了
    private final Encoding properties;
    
    //只有一个有参构造器的情况下，参数就会从容器中拿
    public HttpEncodingAutoConfiguration(HttpProperties properties) {
        this.properties = properties.getEncoding();
    }

    
    //@Bean:给容器中添加一个组件这个组件的某些值需要从properties中获取
    @Bean
    @ConditionalOnMissingBean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
        filter.setEncoding(this.properties.getCharset().name());
  	filter.setForceRequestEncoding(this.properties.shouldForce(org.springframework.boot.autoconfigure.http.HttpProperties.Encoding.Type.REQUEST));
filter.setForceResponseEncoding(this.properties.shouldForce(org.springframework.boot.aut	oconfigure.http.HttpProperties.Encoding.Type.RESPONSE));
        return filter;
}
```

根据当前不同的条件判断，决定这个配置类是否生效？

一旦这个配置类生效；这个配置类就会给容器中添加各种组件；这些组件的属性是从对应得properties中获取的，这些类里面的每一个属性又是和配置文件绑定的



5）所有在配置文件中能配置的属性都是在xxxProperties类中封装着；配置文件能配置什么就可以参照某个功能对应的这个属性类

```java
//从配置文件中获取指定的值和bean的属性进行绑定
@ConfigurationProperties(prefix = "spring.http")
public class HttpProperties {
    private boolean logRequestDetails;
    private final HttpProperties.Encoding encoding = new HttpProperties.Encoding();
```

#### 2.细节

##### 1.@Conditional派生注解 ( Spring注解版原生的@Conditional作用)

作用:必须是@Conditional指定的条件成立,才给容器中添加组件,配置配里面的所有内容才生效; 

| @Conditional扩展注解            | 作用(判断是否满足当前指定条件)                     |
| :------------------------------ | :------------------------------------------------- |
| @ConditionalOnJava              | 系统的java版本是否符合要求                         |
| @ConditionalOnBean              | 容器中存在指定Bean ;                               |
| @ConditionalOnMissingBean       | 容器中不存在指定Bean ;                             |
| @ConditionalOnExpression        | 满足SpEL表达式指定                                 |
| @ConditionalOnClass             | 系统中有指定的类                                   |
| @ConditionalOnMissingClass      | 系统中没有指定的类                                 |
| @ConditionalOnSingleCandidate   | 容器中只有一一个指定的Bean ,或者这个Bean是首选Bean |
| @ConditionalOnProperty          | 系统中指定的属性是否有指定的值                     |
| @ConditionalOnResource          | 类路径下是否存在指定资源文件                       |
| @ConditionalOnWebApplication    | 当前是web环境                                      |
| @ConditionalOnNotWebApplication | 当前不是web环境                                    |
| @ConditionalOnJndi              | JNDI存在指定项                                     |

**自动配置类必须在一定的条件下才能生效；**

我们怎么知道哪些自动配置类生效；

我们可以通过启用 debug=true属性 ；来让控制台答应自动配置报告，这样我们就可以很方便地知道哪些自动配置类生效；

**启用的：**

```java
============================
CONDITIONS EVALUATION REPORT
============================


Positive matches:(自动配置类启用的)
-----------------

   AopAutoConfiguration matched:
      - @ConditionalOnProperty (spring.aop.auto=true) matched (OnPropertyCondition) 
```

**没启用的：**

```java
Negative matches:
-----------------

   ActiveMQAutoConfiguration:
      Did not match:(没有启用，没有匹配成功的自动配置类)
         - @ConditionalOnClass did not find required class 'javax.jms.ConnectionFactory' (OnClassCondition)
```

## SpringBoot使用

### 精髓：

​	**1）、SpringBoot启动会加载大量的自动配置类**

​	**2）、我们看我们需要的功能有没有SpringBoot写好的自动配置类；**

​	**3）、我们再来看这个自动配置类中到底配置了哪些组件；（只要我们要用的组件有,我们就不需要再配置了）**

​	**4）、给容器中自动配置类添加组件的时候，会从properties类中获取某些属性，我们就可以在配置文件中指定这些属性的值；**



## 三、日志

#### 1、日志框架

小张;开发-一个大型系统;
	1、System.out.println("") ;将关键数据打印在控制台;去掉?写在一一个文件?
	2、框架来记录系统的- -些运行时信息;日志框架; zhanglogging.jar ;
	3、高大. 上的几个功能?异步模式?自动归档? xxxx ? zhanglogging-good.jar ?
	4、将以前框架卸下来?换上新的框架,重新修改之前相关的API ; zhanglogging prefect.jar ;
	5、JDBC--数据库驱动;
	写了一个统- -的接口层;日志门面(日志的一个抽象层) ; logging-abstract.jar ;
	给项目中导入具体的日志实现就行了;我们之前的日志框架都是实现的抽象层；



**市面上的日志框架;**
jUL、JCL、Jboss-logging、logback. log4j log4j2、 slf4j...

| ~~日志门面(日志的抽象层)~~                                   | ~~日志实现~~                                           |
| :----------------------------------------------------------- | ------------------------------------------------------ |
| ~~JCL ( Jakarta Commons Logging)~~  **SLF4j ( Simple Logging)**<br>Facade for Java)    ~~jboss-logging~~ | Log4j JUL ( java.util.logging ) <br>Log4j2 **Logback** |

左边选一个门面（抽象层），右边选一个实现；

日志门面：SLF4j;

日志实现：Logback;



SpringBoot:底层是Spring框架，Spring默认使用的JCL;

**SpringBoot选用SLF4j和Logback**

#### 2、SLF4j使用

##### 1.如何在系统中使用SLF4j

以后开发的时候，日志记录方法的调用，不应该直接调用日志的实现类，而是调用日志抽象层里面的方法；

给系统里边导入SLF4j.jar和logback的实现jar

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    logger.info("Hello World");
  }
}
```

图示

‪![SLF4j](C:\Users\隔山海\Desktop\SpringBoot\img\SLF4j.png)

每一个日志的实现框架都有自己的配置文件。使用SLF4j以后，**配置文件还是做成日志实现框架自己本身的配置文件**；

##### 2.遗留问题

a ( slf4j+logback ) : Spring ( commons-logging )、Hibernate ( jboss-logging)、MyBatis、 xxXx
统-日志记录,即使是别的框架和我一起统一使用slf4j进行输出 ?

![legacy](C:\Users\隔山海\Desktop\SpringBoot\img\legacy.png)

**如何让系统中所有的日志都统一到slf4j;**

1、将系统中其他日志框架先排除出去；

2、用中间包来替换原有的日志框架；

3、我们导入slf4j其他的实现



#### 3、SpringBoot日志关系

```xml
<!--启动器-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
```

SpringBoot使用它来做日志功能；

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-logging</artifactId>
  <version>2.2.5.RELEASE</version>
  <scope>compile</scope>
</dependency>
```

![SLF4j-api](C:\Users\隔山海\Desktop\SpringBoot\img\SLF4j-api.png)

总结：

​	1）、SpringBoot也是使用slf4j将logback方式进行日志记录

​	2）、SpringBoot也把其他的日志都替换成了slf4j形；

​	3）、中间替换包？

```java
@SuppressWarnings("rawtypes")
public abstract class LogFactory {
static String UNSUPPORTED_ OPERATION_ _IN_ _JCL_ OVER_ _SLF4J =
"http://www.slf4j.org/codes.html#unsupported_operation_in_jcl_ over_slf4j";
static LogFactory logFactory = new SLF4JLogFactory();
```

![中间转换包](C:\Users\隔山海\Desktop\SpringBoot\img\中间转换包.png)

​	4）、如果我们要引入其他框架，一定要把此框架的默认日志依赖移除掉；

​		Spring框架用的是commons-logging;

```xml
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-core</artifactId>
	<exclusions>
		<exclusion>
			<groupId>commons-logging</groupId>
			<artifactId>commons-logging</artifactId>
		</exclusion>
	</exclusions>
</dependency>
```

**SpringBoot能自动适配所有的日志。而且底层使用slf4j+logback的方式记录日志，引入其他框架的时候。值需要把这个框架依赖的日志框架排除掉；**

#### 4、日志使用

##### 1、默认配置

​	SpringBoot默认帮我们配置好了日志；

```java
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
```

```java
	日志输出格式:
		%d表示日期时间，
		%thread表示线程名，
		%-5level :级别从左显示5个字符宽度
		%logger{50}表示logger名字最长50个字符，否则按照句点分割。
		%msg:日志消息，
		%n是换行符
	%d{yyyy-MM-dd HH:mm:ss.SSS} [ %thread] %-51eve1 %logger{50} - %msg%n
```

SpringBoot修改日志的默认配置

```properties
#日志配置
logging.level.com.myspringboot=trace

#不指定路径在当前项目下生成springboot.log日志
#logging.file=springboot.log

#可以指定完整的路径
#logging.file=e:/spring.log

#在当前磁盘的根路径下创建spring文件夹和log文件夹；使用默认的spring.log作为默认文件
logging.path=/spring/log

#在控制台输出的日志格式
logging.pattern.console=%d{yyyy-MM-dd} [ %thread] %-51eve1 %logger{50} - %msg%n

#指定文件中日志输出的格式
logging.pattern.file=%d{yyyy-MM-dd} === [ %thread] === %-51eve1 === %logger{50} === %msg%n
```



| logging.file | logging.path | Example  | Description                      |
| ------------ | ------------ | -------- | -------------------------------- |
| (none)       | (none)       |          | 只在控制台输出                   |
| 指定文件名   | (none)       | my.log   | 输出日志到my.log文件             |
| (none)       | 指定目录     | /var/log | 输出到指定目录的spring.log文件中 |



##### 2、指定配置

给类路径下放上每一个日志框架自己的配置文件即可；SpringBoot就不再使用默认的配置文件了

| Logging System         | Customization                                                |
| ---------------------- | ------------------------------------------------------------ |
| Logback                | ogback-spring. xml , logback-spring . groovy, logback. xml or <br> logback . groovy |
| Log4j2                 | log4j2-spring.xml or 1og4j2. xml                             |
| JDK Java Util Logging) | logging. properties                                          |

logback.xml:直接就被日志框架识别了

**logback-spring.xml**:日志框架就不直接加载日志的配置项，由SpringBoot解析日志配置，可以使用SpringBoot的高级Profile功能

```xml
<springProfile name="staging">
	<!-- configuration to be enabled when the "staging" profile is active -->
	可以指定某段配置只在某个环境下生效
</springProfile>
```

否则就会报错

```java
no applicable action for [springProfile]
```

```xml
<appender name=" stdout" class="ch. qos . logback . core . ConsoleAppender">
<!--
	日志输出格式:
	%d表示日期时间，
	%thread表示线程名，
	%-5level :级别从左显示5个字符宽度
	%logger{50}表示logger名字最长50个字符，否则按照句点分割。
	%msg:日志消息，
	%n是换行符
-->
<layout class="ch. qos . logback.classic . PatternLayout" >
	<springProfile name="dev">
		<pattern>%d{yyyy-MM-dd HH:mm:Ss.SSS} ----> [%thread] ---> %-5level
%logger{50} - %msg%n</pattern>
	</springProfile>
	<springProfile name=" !dev">
		<pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} ==== [%thread] ==== %-5level %logger{50}
- %msg%n</pattern>
	</springProfile>
	</layout>
</appender>

```

#### 5、切换日志框架

可以按照slf4j的日志适配图,进行相关的切换;
slf4j+log4j的方式;

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web</artifactId>
<exclusions>
	<exclusion>
		<artifactId>logback-classic</artifactId>
		<groupId>ch.qos.logback</groupId>
	</exclusion>
<exclusion>
	<artifactId>log4j-over-s1f4j</artifactId>
		<groupId>org.slf4j</groupId>
	</exclusion>
</exclusions
</dependency>

<dependency>
	<groupId>org. slf4j</ groupId>
	<artifactId>slf4j-log4j12</artifactId>
</dependency>

```