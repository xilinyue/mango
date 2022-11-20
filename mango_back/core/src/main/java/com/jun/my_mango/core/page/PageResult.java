package com.jun.my_mango.core.page;

import java.util.List;

/**
 * @Description: 分页返回接口
 * @author: Liusu
 * @date: 2022年11月10日14:47
 */
public class PageResult {
    private int pageNum; // 当前页码

    private int pageSize;  // 每页数量

    private long totalSize; // 记录总数

    private int totalPages;  // 页码总数

    private List<?> content; // 分页数据

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

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }
}
