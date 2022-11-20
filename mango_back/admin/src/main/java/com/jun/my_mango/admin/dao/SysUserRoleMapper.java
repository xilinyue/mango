package com.jun.my_mango.admin.dao;

import com.jun.my_mango.admin.model.SysUserRole;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserRole row);

    int insertSelective(SysUserRole row);

    SysUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRole row);

    int updateByPrimaryKey(SysUserRole row);

    List<SysUserRole> findUserRoles(@Param(value="userId") Long userId);

    int deleteByUserId(@Param(value = "userId") Long id);
}