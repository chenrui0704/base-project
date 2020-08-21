package com.base.frame.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer id;
    private String username;
    private String loginName;
    private String password;
    private String phone;
    private String email;
    private String identityNumber;

    private Integer state;
    private Integer roleId;
    private String roleName;
    private Integer createId;
    private Date createTime;
    private Integer updateId;
    private Date updateTime;

    private Integer size;
    private Integer index;

}
