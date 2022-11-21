package com.jun.my_mango.admin.dao;

import com.jun.my_mango.admin.model.SysRoleMenu;
import org.apache.ibatis.annotations.Param;

public interface SysRoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleMenu row);

    int insertSelective(SysRoleMenu row);

    SysRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleMenu row);

    int updateByPrimaryKey(SysRoleMenu row);

    int deleteByRoleId(@Param(value = "roleId") Long roleId);
}