package com.base.frame.service;

import com.base.frame.dao.UserMapper;
import com.base.frame.dao.base.AuthorityMapper;
import com.base.frame.entity.User;
import com.base.frame.util.BaseConfig;
import com.base.frame.util.MyResult;
import com.base.frame.util.TokenUtil;
import com.base.frame.util.VerifyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AuthorityMapper authorityMapper;

    @Autowired
    BaseConfig baseConfig;

    public MyResult login(User user) {
        String loginName;
        String password;
        try {
            loginName = new String(Base64.getDecoder().decode(user.getLoginName()));
            password = new String(Base64.getDecoder().decode(user.getPassword()));
        } catch (Exception e) {
            return MyResult.error("用户名或密码错误");
        }

        User o = userMapper.queryByLoginName(loginName);
        if(VerifyUtil.isNull(o)){
            return MyResult.error("用户名或密码错误");
        }

        String inputPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!inputPassword.equals(o.getPassword())){
            return MyResult.error("用户名或密码错误");
        }

        return loginSuccess(o);
    }

    private MyResult loginSuccess(User user){
        String token = TokenUtil.generator(user.getId(), baseConfig.getAesKey());

        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        user.setPassword(null);
        data.put("user", user);

        // 判断当前用户的角色是否有效, 如果有效的话查询对应的菜单权限
        if(!VerifyUtil.isNull(user.getRoleName())){
            List<String> userAuthority = authorityMapper.queryUserAuthorityByRoleId(user.getRoleId());
            data.put("userAuthority", userAuthority);
        }

        return MyResult.success(data);
    }

    public MyResult query(User user) {
        PageHelper.startPage(user.getIndex(), user.getSize());
        List<User> list = userMapper.query(user);
        return MyResult.success(new PageInfo(list));
    }

    public MyResult save(User user){
        if(0 == user.getId()){
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            userMapper.insert(user);
        }else{
            user.setUpdateTime(new Date());
            userMapper.updateById(user);
        }
        return MyResult.success();
    }

    public MyResult updateUserState(User user){
        userMapper.updateUserState(user);
        return MyResult.success();
    }

    public MyResult resetPassword(User user){
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.resetPassword(user);
        return MyResult.success();
    }

}
