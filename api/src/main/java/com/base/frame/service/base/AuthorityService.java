package com.base.frame.service.base;

import com.base.frame.dao.base.AuthorityMapper;
import com.base.frame.entity.base.TypeCode;
import com.base.frame.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService {

    @Autowired
    AuthorityMapper authorityMapper;

    public MyResult queryByRoleId(Integer roleId){
        List<TypeCode> list = authorityMapper.queryByRoleId(roleId);
        return list.size() > 0 ? MyResult.success(list): MyResult.error("暂无数据","1001");
    }

    public MyResult save(Integer roleId, Integer [] menuIds){
        authorityMapper.deleteByRoleId(roleId);
        authorityMapper.save(roleId, menuIds);
        return MyResult.success();
    }


}
