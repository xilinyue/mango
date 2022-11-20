package com.jun.my_mango.admin.service;

import com.jun.my_mango.admin.model.SysMenu;
import com.jun.my_mango.core.service.CurdService;

import java.util.List;

/**
 * 菜单管理
 */
public interface SysMenuService extends CurdService<SysMenu> {


    /**
     * 根据用户名查找菜单列表
     * @param userName
     * @return
     */
    List<SysMenu> findByUser(String userName);

    /**
     * 查询菜单树，用户id和用户名为空则查询全部
     * @param userName
     * @param menuType 获取菜单类型，0：获取所有菜单，包含按钮，1：获取所有菜单，不包含按钮
     * @return
     */
    List<SysMenu> findTree(String userName, int menuType);
}
