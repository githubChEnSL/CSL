package com.mvueproject.mvueproject.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author 隔山海
 */
@Data
public class Department implements Serializable{
    private  Integer departId;
    private  String departName;
}
