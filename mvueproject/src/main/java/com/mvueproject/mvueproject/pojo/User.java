package com.mvueproject.mvueproject.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 隔山海
 * @date 2020/2/10
 */
@Data
public class User implements Serializable {
    private String userId;
    private String userName;
    private Integer userSex;
    private Integer userAge;
    private String userBirthday;
    private Integer userDepartment;
    private String createdTime;
    private String createdBy;
    private String updateTime;
    private String updateBy;
    private Integer version;
    private Integer deleted;

    /**
     * 连表查询
     */
    private Department department;
}
