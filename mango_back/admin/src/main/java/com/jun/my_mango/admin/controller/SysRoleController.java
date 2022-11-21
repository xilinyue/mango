package com.jun.my_mango.admin.controller;

import com.jun.my_mango.admin.constant.SysConstants;
import com.jun.my_mango.admin.dao.SysRoleMapper;
import com.jun.my_mango.admin.model.SysRole;
import com.jun.my_mango.admin.model.SysRoleMenu;
import com.jun.my_mango.admin.model.SysUser;
import com.jun.my_mango.admin.service.SysRoleService;
import com.jun.my_mango.core.http.HttpResult;
import com.jun.my_mango.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 菜单控制器
 * @author: Liusu
 * @date: 2022年11月12日12:35
 */
@RestController
@RequestMapping("/role")
@Api(tags = "E.角色管理模块")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @PreAuthorize("hasAuthority('sys:role:view')")
    @GetMapping(value="/findAll")
    @ApiOperation(value = "获取所有角色")
    public HttpResult findAll() {
        return HttpResult.ok(sysRoleService.findAll());
    }

    @PreAuthorize("hasAuthority('sys:role:add') AND hasAuthority('sys:role:edit')")
    @PostMapping(value="/save")
    @ApiOperation(value = "新增角色")
    public HttpResult save(@RequestBody SysRole record){
        SysRole role = sysRoleService.findById(record.getId());
        if (role != null) {
            if (SysConstants.ADMIN.equalsIgnoreCase(role.getName())){
                return HttpResult.error("超级管理员不允许修改");
            }
        }
        // 新增角色
        if ((record.getId() == null || record.getId() == 0) && !sysRoleService.findByName(record.getName()).isEmpty()) {
            return HttpResult.error("角色名已存在!");
        }
        return HttpResult.ok(sysRoleService.save(record));
    }

    @PreAuthorize("hasAuthority('sys:role:delete')")
    @PostMapping(value="/delete")
    @ApiOperation(value = "删除角色")
    public HttpResult delete(@RequestBody List<SysRole> records) {
        for (SysRole record : records){
            SysRole sysRole  = sysRoleService.findById(record.getId());
            if (sysRole!=null && SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())){
                return HttpResult.error("超级管理员不允许删除");
            }
        }
        return HttpResult.ok(sysRoleService.delete(records));
    }

    @PreAuthorize("hasAuthority('sys:role:view')")
    @PostMapping(value="/findPage")
    @ApiOperation(value = "分页数据")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(sysRoleService.findPage(pageRequest));
    }

    @PreAuthorize("hasAuthority('sys:role:view')")
    @GetMapping(value="/findRoleMenus")
    @ApiOperation(value = "根据角色获取菜单权限")
    public HttpResult findRoleMenus(@RequestParam Long roleId) {
        return HttpResult.ok(sysRoleService.findRoleMenus(roleId));
    }

    @PreAuthorize("hasAuthority('sys:role:view')")
    @PostMapping(value="/saveRoleMenus")
    @ApiOperation(value = "修改新增角色菜单权限")
    public HttpResult saveRoleMenus(@RequestBody List<SysRoleMenu> records) {
        for(SysRoleMenu record:records) {
            SysRole sysRole = sysRoleMapper.selectByPrimaryKey(record.getRoleId());
            if(SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
                // 如果是超级管理员，不允许修改
                return HttpResult.error("超级管理员拥有所有菜单权限，不允许修改！");
            }
        }
        return HttpResult.ok(sysRoleService.saveRoleMenus(records));
    }

}
