package com.base.frame.controller.base;

import com.base.frame.service.base.AuthorityService;
import com.base.frame.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/authority")
public class AuthorityController {

    @Autowired
    AuthorityService authorityService;

    @RequestMapping("/queryByRoleId")
    public MyResult queryByRoleId(Integer roleId){
        return authorityService.queryByRoleId(roleId);
    }

    @RequestMapping("/save")
    public MyResult save(Integer roleId, Integer [] menuIds){
        return authorityService.save(roleId, menuIds);
    }

}
