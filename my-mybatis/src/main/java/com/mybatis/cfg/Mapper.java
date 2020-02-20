package com.mybatis.cfg;/**
 * Description Mapper
 * Created by 隔山海 .
 * date on 2020/2/18.
 */

/**
 * 包含两个部分：1.执行的SQL语句
 *              2.封装结果的实体类全限定类名
 * @ClassName Mapper
 * @Description 用于封装SQL语句和结果类型的全限定类名
 * @Author 隔山海
 * @date 2020/2/18 20:41
 * @Version 1.0
 */
public class Mapper {
    /**
     * SQL语句
     */
    private String queryString;
    /**
     * 实体类的全限定类名
     */
    private String resultType;

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
