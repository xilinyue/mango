package com.jun.my_mango.admin.controller;

import com.jun.my_mango.admin.model.SysLoginLog;
import com.jun.my_mango.admin.service.SysLoginLogService;
import com.jun.my_mango.core.http.HttpResult;
import com.jun.my_mango.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 登录日志控制器
 * @author: Liusu
 * @date: 2022年11月12日16:08
 */
@RestController
@RequestMapping(value = "/loginlog")
@Api(tags = "F.登录日志模块")
public class SysLoginLogController {
    @Autowired
    private SysLoginLogService sysLoginLogService;

    @PreAuthorize("hasAuthority('sys:loginlog:view')")
    @PostMapping(value = "/findPage")
    @ApiOperation(value = "获取登录日志数据")
    public HttpResult findPage(@RequestBody PageRequest pageRequest){
        return HttpResult.ok(sysLoginLogService.findPage(pageRequest));
    }

    @PreAuthorize("hasAuthority('sys:loginlog:delete')")
    @PostMapping(value="/delete")
    @ApiOperation(value = "删除登录日志")
    public HttpResult delete(@RequestBody List<SysLoginLog> records) {
        return HttpResult.ok(sysLoginLogService.delete(records));
    }

}
