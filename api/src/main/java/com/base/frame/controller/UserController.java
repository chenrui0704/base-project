package com.base.frame.controller;

import com.base.frame.entity.User;
import com.base.frame.service.UserService;
import com.base.frame.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public MyResult login(@RequestBody User user){
        return userService.login(user);
    }

    @RequestMapping("/query")
    public MyResult query(){
        return userService.query();
    }

}
