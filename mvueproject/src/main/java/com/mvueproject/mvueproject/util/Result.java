package com.mvueproject.mvueproject.util;

import lombok.Data;

/**
 * @ClassName Result
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/10 16:12
 * @Version 1.0
 */
@Data
public class Result {
    /**
     * 状态码：200表示正常，400表示失败
     */
    private Integer code;
    /**
     * 失败的情况下的提示文字
     */
    private  String msg;
    /**
     * 返回的数据
     */
    private Object data;

    public Result() {
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(String msg, Object data) {
        //默认成功
        this.code = 200;
        this.msg = msg;
        this.data = data;
    }

    public Result(Object data) {
        this.code = 200;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(String msg) {
        this.code=200;
        this.msg = msg;
    }
}
