package com.base.frame.dao;

import com.base.frame.entity.User;

import java.util.List;

public interface UserMapper {

    List<User> query(User user);

    User queryByLoginName(String loginName);

    void insert(User user);

    void updateUserState(User user);

    void resetPassword(User user);

    void updateById(User user);

}
