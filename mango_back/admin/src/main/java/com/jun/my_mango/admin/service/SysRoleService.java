package com.jun.my_mango.admin.service;

import com.jun.my_mango.admin.model.SysMenu;
import com.jun.my_mango.admin.model.SysRole;
import com.jun.my_mango.admin.model.SysRoleMenu;
import com.jun.my_mango.core.service.CurdService;

import java.util.List;

/**
 * @Description: 角色管理
 * @author: Liusu
 * @date: 2022年11月12日14:08
 */
public interface SysRoleService extends CurdService<SysRole> {

    /**
     * 查询全部
     * @return
     */
    List<SysRole> findAll();

    /**
     * 根据角色名查询角色
     * @param name
     * @return
     */
    List<SysRole> findByName(String name);

    /**
     * 查询角色菜单集合
     * @param roleId
     * @return
     */
    List<SysMenu> findRoleMenus(Long roleId);

    /**
     * 保存角色菜单
     * @param records
     * @return
     */
    int saveRoleMenus(List<SysRoleMenu> records);
}
