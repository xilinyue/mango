package com.jun.my_mango.admin.service.impl;

import com.jun.my_mango.admin.dao.SysDictMapper;
import com.jun.my_mango.admin.model.SysDict;
import com.jun.my_mango.admin.service.SysDictService;
import com.jun.my_mango.core.page.MybatisPageHelper;
import com.jun.my_mango.core.page.PageRequest;
import com.jun.my_mango.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODO
 * @author: Liusu
 * @date: 2022年11月10日16:00
 */
@Service
public class SysDictServiceImpl implements SysDictService {
    @Autowired
    private SysDictMapper sysDictMapper;

    /**
     * 根据名称获取所有
     * @param label
     * @return
     */
    @Override
    public List<SysDict> findByLabel(String label) {
        return sysDictMapper.findByLabel(label);
    }

    /**
     * 新增或者更新（如果id存在则更新）
     * @param record
     * @return
     */
    @Override
    public int save(SysDict record) {
        if (record.getId() == null || record.getId() == 0){
            return sysDictMapper.insertSelective(record);
        }
        return sysDictMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据id删除
     * @param record
     * @return
     */
    @Override
    public int delete(SysDict record) {
        return sysDictMapper.deleteByPrimaryKey(record.getId());
    }

    /**
     * 批量删除
     * @param records
     * @return
     */
    @Override
    public int delete(List<SysDict> records) {
        for (SysDict record : records){
            delete(record);
        }
        return 1;
    }

    @Override
    public SysDict findById(Long id) {
        return sysDictMapper.selectByPrimaryKey(id);
    }

    /**
     * 分页查询
     * @param pageRequest 自定义，统一分页查询请求
     * @return
     */
    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParam("label");
        if (label != null){
            return MybatisPageHelper.findPage(pageRequest, sysDictMapper, "findPageByLabel", label);
        }
        return MybatisPageHelper.findPage(pageRequest,sysDictMapper);
    }
}
