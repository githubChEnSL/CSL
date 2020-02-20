package com.mybatis.io;
/**
 * Description io
 * Created by 隔山海 .
 * date on 2020/2/18.
 */

import java.io.InputStream;

/**
 * 类加载器：读取配置文件的类
 * @ClassName Resources
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/18 13:12
 * @Version 1.0
 */
public class Resources {

    public static InputStream getResourceAsStream(String filePath) {
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
