package com.base.frame.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer id;
    private String username;
    private String loginName;
    private String password;
    private Integer createId;
    private Date createTime;

}
