package com.jun.my_mango.admin.dao;

import com.jun.my_mango.admin.model.SysConfig;

public interface SysConfigMapper {
    int insert(SysConfig row);

    int insertSelective(SysConfig row);
}