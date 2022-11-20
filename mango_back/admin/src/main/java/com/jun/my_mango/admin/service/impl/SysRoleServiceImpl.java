package com.jun.my_mango.admin.service.impl;

import com.jun.my_mango.admin.dao.SysRoleMapper;
import com.jun.my_mango.admin.model.SysRole;
import com.jun.my_mango.admin.service.SysRoleService;
import com.jun.my_mango.core.page.PageRequest;
import com.jun.my_mango.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODO
 * @author: Liusu
 * @date: 2022年11月12日14:09
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Override
    public List<SysRole> findAll() {
        return sysRoleMapper.findAll();
    }

    @Override
    public int save(SysRole record) {
        return 0;
    }

    @Override
    public int delete(SysRole record) {
        return 0;
    }

    @Override
    public int delete(List<SysRole> records) {
        return 0;
    }

    @Override
    public SysRole findById(Long id) {
        return null;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return null;
    }
}
