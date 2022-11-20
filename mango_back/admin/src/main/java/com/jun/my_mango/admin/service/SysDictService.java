package com.jun.my_mango.admin.service;

import com.jun.my_mango.admin.model.SysDict;
import com.jun.my_mango.core.service.CurdService;

import java.util.List;

/**
 * 字典管理
 */
public interface SysDictService extends CurdService<SysDict> {
    /**
     * 根据名称查询
     * @param label
     * @return
     */
    List<SysDict> findByLabel(String label);
}
