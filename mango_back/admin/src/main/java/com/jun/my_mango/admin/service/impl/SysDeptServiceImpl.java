package com.jun.my_mango.admin.service.impl;

import com.jun.my_mango.admin.dao.SysDeptMapper;
import com.jun.my_mango.admin.model.SysDept;
import com.jun.my_mango.admin.service.SysDeptService;
import com.jun.my_mango.core.page.MybatisPageHelper;
import com.jun.my_mango.core.page.PageRequest;
import com.jun.my_mango.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: TODO
 * @author: Liusu
 * @date: 2022年11月14日17:02
 */
@Service
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public int save(SysDept record) {
        if (record.getId() == null || record.getId() == 0){
//            record.setCreateBy((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//            record.setCreateTime(new Date());
            return sysDeptMapper.insertSelective(record);
        }
//        record.setLastUpdateBy((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        record.setLastUpdateTime(new Date());
        return sysDeptMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysDept record) {
        return sysDeptMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysDept> records) {
        for (SysDept record : records){
            delete(record);
        }
        return 1;
    }

    @Override
    public SysDept findById(Long id) {
        return sysDeptMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, sysDeptMapper);
    }

    @Override
    public List<SysDept> findTree() {
        List<SysDept> sysDepts = new ArrayList<>();
        List<SysDept> depts = sysDeptMapper.findAll();
        for (SysDept dept : depts){
            if (dept.getParentId() == null || dept.getParentId() == 0){
                dept.setLevel(0);
                sysDepts.add(dept);
            }
        }
        findChildren(sysDepts, depts);
        return sysDepts;
    }

    private void findChildren(List<SysDept> sysDepts, List<SysDept> depts){
        for (SysDept sysDept : sysDepts){
            List<SysDept> children = new ArrayList<>();
            for (SysDept dept : depts){
                if (sysDept.getId() != null && sysDept.getId().equals(dept.getParentId())){
                    dept.setParentName(dept.getName());
                    dept.setLevel(sysDept.getLevel() + 1);
                    children.add(dept);
                }
            }
            sysDept.setChildren(children);
            findChildren(children, depts);
        }
    }
}
