package com.jun.my_mango.admin.dao;

import com.jun.my_mango.admin.model.SysLog;
import com.jun.my_mango.admin.model.SysLoginLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysLoginLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysLoginLog row);

    int insertSelective(SysLoginLog row);

    SysLoginLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLoginLog row);

    int updateByPrimaryKey(SysLoginLog row);

    List<SysLog> findPage();

    List<SysLog> findPageByUserName(@Param(value="userName") String userName);

    List<SysLog> findPageByStatus(@Param(value="status") String status);
}