package com.base.frame.dao.base;

import com.base.frame.entity.base.TypeCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorityMapper {

    List<TypeCode> queryByRoleId(Integer roleId);

    void deleteByRoleId(Integer roleId);

    void save(@Param("roleId") Integer roleId, @Param("menuIds") Integer [] menuIds);
}
