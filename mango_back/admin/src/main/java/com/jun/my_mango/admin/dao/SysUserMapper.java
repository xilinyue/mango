package com.jun.my_mango.admin.dao;

import com.jun.my_mango.admin.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser row);

    int insertSelective(SysUser row);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser row);

    int updateByPrimaryKey(SysUser row);

    /**
     * 查询所有用户
     * @return
     */
     List<SysUser> findAll();

     /**
     * 分页查询
     * @return
     */
    List<SysUser> findPage();

    SysUser findByName(@Param(value = "name") String username);

    List<SysUser> findPageByName(@Param(value="name") String name);

    List<SysUser> findPageByNameAndEmail(@Param(value="name") String name, @Param(value="email") String email);
}