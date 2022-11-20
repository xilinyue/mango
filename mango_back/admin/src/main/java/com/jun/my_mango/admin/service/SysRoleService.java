package com.jun.my_mango.admin.service;

import com.jun.my_mango.admin.model.SysRole;
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
}
