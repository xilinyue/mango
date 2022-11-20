package com.jun.my_mango.admin.dao;

import com.jun.my_mango.admin.model.SysDept;

import java.util.List;

public interface SysDeptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysDept row);

    int insertSelective(SysDept row);

    SysDept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDept row);

    int updateByPrimaryKey(SysDept row);

    List<SysDept> findAll();

    List<SysDept> findPage();
}