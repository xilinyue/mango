package com.jun.my_mango.admin.service;

import com.jun.my_mango.admin.model.SysUser;
import com.jun.my_mango.admin.model.SysUserRole;
import com.jun.my_mango.core.page.PageRequest;
import com.jun.my_mango.core.service.CurdService;

import java.io.File;
import java.util.List;
import java.util.Set;

public interface SysUserService extends CurdService<SysUser> {
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    SysUser findByName(String username);

    /**
     * 查找所有用户
     * @return
     */
    List<SysUser> findAll();

    /**
     * 生成用户信息excel文件
     * @param pageRequest
     * @return
     */
    File createUserExcelFile(PageRequest pageRequest);


    /**
     * 根据用户名查找用户权限
     * @param userName
     * @return
     */
    Set<String> findPermissions(String userName);

    /**
     * 查找用户的角色集合
     * @param userId
     * @return
     */
    List<SysUserRole> findUserRoles(Long userId);
}
