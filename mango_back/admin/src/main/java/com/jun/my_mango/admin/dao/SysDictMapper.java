package com.jun.my_mango.admin.dao;

import com.jun.my_mango.admin.model.SysDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDictMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysDict row);

    int insertSelective(SysDict row);

    SysDict selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDict row);

    int updateByPrimaryKey(SysDict row);

    /**
     * 分页查询
     * @return
     */
    List<SysDict> findPage();

    /**
     * 根据标签名称查询
     * @param label
     * @return
     */
    List<SysDict> findByLabel(@Param(value = "label") String label);

    /**
     * 根据标签名称分页查询
     * @param label
     * @return
     */
    List<SysDict> findPageByLabel(@Param(value = "label") String label);
}