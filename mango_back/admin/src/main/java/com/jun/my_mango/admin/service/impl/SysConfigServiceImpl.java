package com.jun.my_mango.admin.service.impl;

import com.jun.my_mango.admin.dao.SysConfigMapper;
import com.jun.my_mango.admin.model.SysConfig;
import com.jun.my_mango.admin.service.SysConfigService;
import com.jun.my_mango.core.page.MybatisPageHelper;
import com.jun.my_mango.core.page.PageRequest;
import com.jun.my_mango.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODO
 * @author: Liusu
 * @date: 2022年12月03日17:44
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {
    @Autowired
    private SysConfigMapper sysConfigMapper;

    @Override
    public List<SysConfig> findByLabel(String lable) {
        return sysConfigMapper.findByLabel(lable);
    }

    @Override
    public int save(SysConfig record) {
        if(record.getId() == null || record.getId() == 0) {
            return sysConfigMapper.insertSelective(record);
        }
        return sysConfigMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysConfig record) {
        return sysConfigMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysConfig> records) {
        for(SysConfig record:records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysConfig findById(Long id) {
        return sysConfigMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParam("label");
        if(label != null) {
            return MybatisPageHelper.findPage(pageRequest, sysConfigMapper, "findPageByLabel", label);
        }
        return MybatisPageHelper.findPage(pageRequest, sysConfigMapper);
    }
}
