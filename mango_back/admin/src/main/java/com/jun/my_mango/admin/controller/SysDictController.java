package com.jun.my_mango.admin.controller;

import com.jun.my_mango.admin.model.SysDict;
import com.jun.my_mango.admin.service.SysDictService;
import com.jun.my_mango.core.http.HttpResult;
import com.jun.my_mango.core.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: TODO
 * @author: Liusu
 * @date: 2022年11月10日16:07
 */
@RestController
@RequestMapping(value = "/dict")
@Api(value = "字典模块", tags = "B.字典模块")
public class SysDictController {
    @Autowired
    private SysDictService sysDictService;

    // 新增
    @PostMapping(value = "/save")
    @ApiOperation(value = "新增字典")
    @PreAuthorize("hasAuthority('sys:dict:add') AND hasAuthority('sys:dict:edit')")
    public HttpResult save(@RequestBody SysDict sysDict){
        return HttpResult.ok(sysDictService.save(sysDict));
    }

    // 批量删除
    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除字典")
    @PreAuthorize("hasAuthority('sys:dict:delete')")
    public HttpResult delete(@RequestBody List<SysDict> records){
        return HttpResult.ok(sysDictService.delete(records));
    }

    // 分页查询
    @PostMapping(value = "/findPage")
    @ApiOperation(value = "分页查看字典")
    @PreAuthorize("hasAuthority('sys:dict:view')")
    public HttpResult findPage(@RequestBody PageRequest pageRequest){
        return HttpResult.ok(sysDictService.findPage(pageRequest));
    }

    // 根据标签查询所有
    @GetMapping(value = "/findByLabel")
    @ApiOperation(value = "根据标签查找字典")
    @PreAuthorize("hasAuthority('sys:dict:view')")
    public HttpResult findByLabel(@RequestParam String label){
        return HttpResult.ok(sysDictService.findByLabel(label));
    }


}
