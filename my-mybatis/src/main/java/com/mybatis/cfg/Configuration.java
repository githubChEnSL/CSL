package com.mybatis.cfg;
/**
 * Description Configuration
 * Created by 隔山海 .
 * date on 2020/2/18.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Configuration
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/18 20:22
 * @Version 1.0
 */
public class Configuration {

    private String driver;
    private String url;
    private String username;
    private String password;
    private Map<String,Mapper> Mappers =new HashMap<String, Mapper>();

    public Map<String, Mapper> getMappers() {
        return Mappers;
    }

    public void setMappers(Map<String, Mapper> mapper) {
//        追加
        Mappers.putAll(mapper);
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
