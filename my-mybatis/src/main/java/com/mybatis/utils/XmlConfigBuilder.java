package com.mybatis.utils;
/**
 * Description XmlConfigBuilder
 * Created by 隔山海 .
 * date on 2020/2/18.
 */

import com.mybatis.annotation.Select;
import com.mybatis.cfg.Configuration;
import com.mybatis.cfg.Mapper;
import com.mybatis.io.Resources;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName XmlConfigBuilder
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/18 14:56
 * @Version 1.0
 */
public class XmlConfigBuilder{
    /**
     * 使用技术为：dom4j+xpath
     * @param config
     * @return
     */
    public static Configuration loadConfiguration(InputStream config){
        try {
//            定义封装连接信息的配置对象（mybatis的配置对象）
            Configuration cfg=new Configuration();
//            获取SAXReader对象
            SAXReader reader = new SAXReader();
//            根据字节输入流获取Document对象
            Document document = reader.read(config);
//            获取根节点
            Element root = document.getRootElement();
//            获取所有的property节点
            List<Element> propertyElements = root.selectNodes("//property");
//            遍历节点
            for(Element propertyElement : propertyElements){
//                判断节点是数据库连接中的哪一部分
//                取出name属性
                String name = propertyElement.attributeValue("name");
                if("driver".equals(name)){
//                    表示驱动
//                    获取property标签中的value值
                    String driver = propertyElement.attributeValue("value");
                    cfg.setDriver(driver);
                }
                if("url".equals(name)){
//                    表示连接字符串
//                    获取property标签中的value值
                    String url = propertyElement.attributeValue("value");
                    cfg.setUrl(url);
                }
                if("username".equals(name)){
//                    表示用户名
//                    获取property标签中的value值
                    String username = propertyElement.attributeValue("value");
                    cfg.setUsername(username);
                }
                if("password".equals(name)){
//                    表示密码
//                    获取property标签中的value值
                    String password = propertyElement.attributeValue("value");
                    cfg.setPassword(password);
                }
            }
//            取出mappers中的所有mapper标签，判断他们是属于resource还是class属性
            List<Element> mapperElements = root.selectNodes("//mappers/mapper");
//            遍历集合
            for (Element mapperElement : mapperElements){
//                判断属性
                Attribute attribute=mapperElement.attribute("resource");
                if(attribute != null){
                    System.out.println("使用的是XML");
                    String mapperPath = attribute.getValue();
//                    把映射文件的内容取出来封装成map
                    Map<String, Mapper> mappers=loadMapperConfiguration(mapperPath);
//                    给Configuration中的mappers赋值
                    cfg.setMappers(mappers);
                }else{
                    System.out.println("使用的是注解");
                    String daoClassPath = mapperElement.attributeValue("class");
                    Map<String,Mapper> mappers = loadMapperAnnotation(daoClassPath);
                    cfg.setMappers(mappers);
                }
            }
            return cfg;
        }catch(Exception e){
            throw new RuntimeException();
        }finally {
            try {
                config.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static Map<String, Mapper> loadMapperAnnotation(String daoClassPath) throws Exception{
//        定义返回值对象
        Map<String,Mapper> mappers = new HashMap<String, Mapper>();
//        得到DAO接口的字节码对象
        Class daoClass = Class.forName(daoClassPath);
//        得到dao接口中的方法数组
        Method[] methods = daoClass.getMethods();
//        遍历methods数组
        for (Method method : methods){
//            取出每一个方法，判断是否有select注解
            boolean isAnnotated = method.isAnnotationPresent(Select.class);
            if(isAnnotated){
//                创建Mapper对象
                Mapper mapper = new Mapper();
//                取出注解的Value属性值
                Select SelectAnno = method.getAnnotation(Select.class);
                String queryString = SelectAnno.value();
                mapper.setQueryString(queryString);
//                获取当前方法的返回值，还要求不许带有泛型信息
                Type type = method.getGenericReturnType();
//                判断type是否为参数化类型
                if(type instanceof ParameterizedType){
//                    强转
                    ParameterizedType ptype = (ParameterizedType) type;
//                    得到参数化类型中的实际类型参数
                    Type[] types = ptype.getActualTypeArguments();
//                    取出第一个
                    Class domainClass = (Class) types[0];
//                    获取domainClass的类名
                    String resultType = domainClass.getName();
//                    给Mapper赋值
                    mapper.setResultType(resultType);
                }
//                组装key信息
//                获取方法的名称
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();
                String key = className+"."+methodName;
//                给map赋值
                mappers.put(key,mapper);
            }
        }
        return mappers;
    }

    private static Map<String, Mapper> loadMapperConfiguration(String mapperPath) throws IOException {
        InputStream in= null;
        try {
//            定义返回值对象
            Map<String,Mapper> mappers = new HashMap<String, Mapper>();
//            根据路径获取字节输入流
            in = Resources.getResourceAsStream(mapperPath);
//            根据字节输入流获取Document对象
            SAXReader reader = new SAXReader();
            Document document = reader.read(in);
//            获取根节点
            Element root = document.getRootElement();
//            获取根节点的namespace属性取值，是组成map的key的部分
            String namespace = root.attributeValue("namespace");
//            获取所有的select节点
            List<Element> selectElements = root.selectNodes("//select");
            for (Element selectElement : selectElements){
//                取出ID属性的值，  组成map中的key的部分
                String id = selectElement.attributeValue("id");
//                取出resultType属性的值，组成map的value的部分
                String resultType = selectElement.attributeValue("resultType");
//                取出文本内容，组成map中value的部分
                String queryString = selectElement.getText();
//                创建key
                String key = namespace+"."+id;
//                创建value
                Mapper mapper = new Mapper();
                mapper.setQueryString(queryString);
                mapper.setResultType(resultType);
                mappers.put(key,mapper);
            }
            return mappers;
        }catch(Exception e){
            throw new RuntimeException();
        }finally{
            try {
                in.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
