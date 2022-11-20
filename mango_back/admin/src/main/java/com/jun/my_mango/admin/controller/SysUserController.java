package com.jun.my_mango.admin.controller;

import com.jun.my_mango.admin.constant.SysConstants;
import com.jun.my_mango.admin.model.SysUser;
import com.jun.my_mango.admin.service.SysUserService;
import com.jun.my_mango.admin.util.PasswordUtils;
import com.jun.my_mango.core.http.HttpResult;
import com.jun.my_mango.core.page.PageRequest;
import com.jun.my_mango.common.utils.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @Description: TODO
 * @author: Liusu
 * @date: 2022年11月10日12:06
 */
@RestController
@RequestMapping("/user")
@Api(tags = "C.用户管理模块")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @PreAuthorize("hasAuthority('sys:user:view')")
    @PostMapping(value = "/exportExcelUser")
    @ApiOperation(value = "导出用户")
    public void exportExcelUser(@RequestBody PageRequest request, HttpServletResponse response){
        File file = sysUserService.createUserExcelFile(request);
        FileUtils.downloadFile(response, file, file.getName());
    }

    @GetMapping(value = "/findAll")
    public Object findAll() {
        return sysUserService.findAll();
    }

    @PreAuthorize("hasAuthority('sys:user:view')")
    @GetMapping(value="/findByName")
    @ApiOperation(value = "根据用户名获取用户信息")
    public HttpResult findByUserName(@RequestParam String name) {
        return HttpResult.ok(sysUserService.findByName(name));
    }

    @PreAuthorize("hasAuthority('sys:user:view')")
    @GetMapping(value = "/findPermissions")
    @ApiOperation(value = "根据用户名获取菜单权限标识")
    public HttpResult findPermissions(@RequestParam String userName){
        return HttpResult.ok(sysUserService.findPermissions(userName));
    }

    @PreAuthorize("hasAuthority('sys:user:view')")
    @PostMapping(value="/findPage")
    @ApiOperation(value = "分页获取用户数据")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(sysUserService.findPage(pageRequest));
    }

    @PreAuthorize("hasAuthority('sys:user:add') and hasAuthority('sys:user:edit')")
    @PostMapping(value = "/save")
    @ApiOperation(value = "新增用户")
    public HttpResult save(@RequestBody SysUser sysUser){
        SysUser user = sysUserService.findById(sysUser.getId());
        if (user!=null){
            // 说明是修改
            if (SysConstants.ADMIN.equalsIgnoreCase(user.getName())){
                // 不可以对超级管理员进行修改
                return HttpResult.error("超级管理员不允许修改");
            }
        }
        if (sysUser.getPassword() != null){
            String salt = PasswordUtils.getSalt();
            if (user == null) {
                // 用户不存在
                if (sysUserService.findByName(sysUser.getName()) != null){
                    return HttpResult.error("用户名已存在");
                }
                String password = PasswordUtils.encode(sysUser.getPassword(), salt);
                sysUser.setSalt(salt);
                sysUser.setPassword(password);
//                sysUser.setCreateBy((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//                sysUser.setCreateTime(new Date());
//                sysUser.setLastUpdateBy((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//                sysUser.setLastUpdateTime(new Date());

            } else {
                // 修改用户，且修改了密码
                if (!sysUser.getPassword().equals(user.getPassword())){
                    String password = PasswordUtils.encode(sysUser.getPassword(),salt);
                    sysUser.setSalt(salt);
                    sysUser.setPassword(password);
//                    sysUser.setLastUpdateBy((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//                    sysUser.setLastUpdateTime(new Date());
                }
            }
        }
        return HttpResult.ok(sysUserService.save(sysUser));
    }

    @PreAuthorize("hasAuthority('sys:user:delete')")
    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除用户")
    public HttpResult delete(@RequestBody List<SysUser> records){
        for (SysUser record : records){
            SysUser sysUser  = sysUserService.findById(record.getId());
            if (sysUser!=null &&SysConstants.ADMIN.equalsIgnoreCase(sysUser.getName())){
                return HttpResult.error("超级管理员不允许删除");
            }
        }
        return HttpResult.ok(sysUserService.delete(records));
    }

    @PreAuthorize("hasAuthority('sys:user:view')")
    @GetMapping(value="/findUserRoles")
    @ApiOperation(value = "获取用户的角色")
    public HttpResult findUserRoles(@RequestParam Long userId) {
        return HttpResult.ok(sysUserService.findUserRoles(userId));
    }

}
