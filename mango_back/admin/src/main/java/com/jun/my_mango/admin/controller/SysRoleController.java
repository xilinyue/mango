package com.jun.my_mango.admin.controller;

import com.jun.my_mango.admin.service.SysRoleService;
import com.jun.my_mango.core.http.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PreAuthorize("hasAuthority('sys:role:view')")
    @GetMapping(value="/findAll")
    @ApiOperation(value = "获取所有角色")
    public HttpResult findAll() {
        return HttpResult.ok(sysRoleService.findAll());
    }
}
