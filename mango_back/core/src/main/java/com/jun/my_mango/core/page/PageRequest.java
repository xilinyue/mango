package com.jun.my_mango.core.page;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 分页请求
 * @author: Liusu
 * @date: 2022年11月10日14:48
 */
public class PageRequest {

    private  int pageNum = 1;  //当前页码

    private int pageSize = 10;  // 每页数量

    private Map<String, Object> params = new HashMap<String, Object>();  // 查询参数

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
    public Object getParam(String key) {
        return getParams().get(key);
    }
}
