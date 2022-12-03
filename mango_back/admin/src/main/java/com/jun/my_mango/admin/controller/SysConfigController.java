package com.jun.my_mango.admin.controller;

import com.jun.my_mango.admin.model.SysConfig;
import com.jun.my_mango.admin.service.SysConfigService;
import com.jun.my_mango.core.http.HttpResult;
import com.jun.my_mango.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 系统配置控制器
 * @author: Liusu
 * @date: 2022年12月03日17:42
 */
@RestController
@Api(tags = "H.系统配置模块")
@RequestMapping(value = "/config")
public class SysConfigController {
    @Autowired
    private SysConfigService sysConfigService;

    @PreAuthorize("hasAuthority('sys:config:add') AND hasAuthority('sys:config:edit')")
    @PostMapping(value="/save")
    @ApiOperation(value = "新增")
    public HttpResult save(@RequestBody SysConfig record) {
        return HttpResult.ok(sysConfigService.save(record));
    }

    @PreAuthorize("hasAuthority('sys:config:delete')")
    @PostMapping(value="/delete")
    @ApiOperation(value = "删除")
    public HttpResult delete(@RequestBody List<SysConfig> records) {
        return HttpResult.ok(sysConfigService.delete(records));
    }

    @PreAuthorize("hasAuthority('sys:config:view')")
    @PostMapping(value="/findPage")
    @ApiOperation(value = "分页查询")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(sysConfigService.findPage(pageRequest));
    }

    @PreAuthorize("hasAuthority('sys:config:view')")
    @GetMapping(value="/findByLabel")
    @ApiOperation(value = "根据标签查询")
    public HttpResult findByLabel(@RequestParam String lable) {
        return HttpResult.ok(sysConfigService.findByLabel(lable));
    }
}
