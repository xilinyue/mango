package com.jun.my_mango.admin.dao;

import com.jun.my_mango.admin.model.SysRoleDept;

public interface SysRoleDeptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleDept row);

    int insertSelective(SysRoleDept row);

    SysRoleDept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleDept row);

    int updateByPrimaryKey(SysRoleDept row);
}