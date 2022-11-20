package com.jun.my_mango.admin.dao;

import com.jun.my_mango.admin.model.SysRole;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole row);

    int insertSelective(SysRole row);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole row);

    int updateByPrimaryKey(SysRole row);

    List<SysRole> findAll();
}