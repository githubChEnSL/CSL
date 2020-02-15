package com.mvueproject.mvueproject.util;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Page
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/11 1:22
 * @Version 1.0
 */
@Data
public class Page<T> {
    /**
     * 分页下标
     */
    private Integer index;

    /**
     * 当前页数
     */
    private Integer pageNumber;

    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 总条数
     */
    private Integer totalCount;

    /**
     * 数据
     */
    private List<T> list;

    /**
     * 查询参数
     */
    private Map<String,Object> params = new HashMap<>(16);

    /**
     * 排序列
     */
    private String sortColumn;

    /**
     * 排序方式
     * 排序方式作为sql语句的关键字，因此mybatis只能使用${}去进行拼接
     * 这么写存在严重的sql注入问题（druid连接池可以适当得防止sql注入）
     * 应该在执行sql语句之前，对该字段以及排序列字段进行过滤
     * 如果出现了可能会存在注入的关键字，直接将两个字段置为空
     * 可能存在注入的关键子有： #, ,空格 --，union,;,select ,drop
     */
    private String sortMethod;
}
