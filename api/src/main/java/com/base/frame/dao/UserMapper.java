package com.base.frame.dao;

import com.base.frame.entity.User;

import java.util.List;

public interface UserMapper {

    List<User> query();

    User queryByLoginName(String loginName);

}
