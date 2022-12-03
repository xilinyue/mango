package com.jun.my_mango.admin.service;

import com.jun.my_mango.admin.model.SysConfig;
import com.jun.my_mango.core.service.CurdService;

import java.util.List;

/**
 * @Description: 系统配置管理
 * @author: Liusu
 * @date: 2022年12月03日17:43
 */
public interface SysConfigService extends CurdService<SysConfig> {
    /**
     * 根据名称查询
     * @param lable
     * @return
     */
    List<SysConfig> findByLabel(String lable);
}
