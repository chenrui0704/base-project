package com.base.frame.service;

import com.base.frame.dao.UserMapper;
import com.base.frame.entity.User;
import com.base.frame.util.BaseConfig;
import com.base.frame.util.MyResult;
import com.base.frame.util.TokenUtil;
import com.base.frame.util.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

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

        return MyResult.success(data);
    }

    public MyResult query() {
        List<User> list = userMapper.query();
        return MyResult.success(list);
    }

}
