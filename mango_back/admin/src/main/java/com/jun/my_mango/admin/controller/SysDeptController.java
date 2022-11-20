package com.jun.my_mango.admin.controller;

import com.jun.my_mango.admin.model.SysDept;
import com.jun.my_mango.admin.service.SysDeptService;
import com.jun.my_mango.core.http.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 机构控制器
 * @author: Liusu
 * @date: 2022年11月14日16:59
 */
@RestController
@ApiOperation(value = "G.机构管理模块")
@RequestMapping(value = "/dept")
public class SysDeptController {
    @Autowired
    private SysDeptService sysDeptService;

    @PreAuthorize("hasAuthority('sys:dept:add') and hasAuthority('sys:dept:edit')")
    @ApiOperation(value = "新增更新机构")
    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody SysDept record){
        return HttpResult.ok(sysDeptService.save(record));
    }

    @PreAuthorize("hasAuthority('sys:dept:delete')")
    @ApiOperation(value = "删除")
    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody List<SysDept> records){
        return HttpResult.ok(sysDeptService.delete(records));
    }

    @PreAuthorize("hasAuthority('sys:dept:view')")
    @ApiOperation(value = "获取机构树")
    @GetMapping(value = "/findTree")
    public HttpResult findTree(){
        return HttpResult.ok(sysDeptService.findTree());
    }


}
