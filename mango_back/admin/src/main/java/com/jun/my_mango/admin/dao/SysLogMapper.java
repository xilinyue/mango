package com.jun.my_mango.admin.dao;

import com.jun.my_mango.admin.model.SysLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysLog row);

    int insertSelective(SysLog row);

    SysLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLog row);

    int updateByPrimaryKey(SysLog row);

    List<SysLog> findPage();

    List<SysLog> findPageByUserName(@Param(value="userName") String userName);
}