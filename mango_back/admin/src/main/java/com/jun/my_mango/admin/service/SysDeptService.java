package com.jun.my_mango.admin.service;

import com.jun.my_mango.admin.model.SysDept;
import com.jun.my_mango.core.service.CurdService;

import java.util.List;

/**
 * @Description: 机构管理
 * @author: Liusu
 * @date: 2022年11月14日17:01
 */
public interface SysDeptService extends CurdService<SysDept> {
    /**
     * 查询机构树
     * @param
     * @return
     */
    List<SysDept> findTree();
}
