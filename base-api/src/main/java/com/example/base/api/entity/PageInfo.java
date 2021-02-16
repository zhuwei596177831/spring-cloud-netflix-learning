package com.example.base.api.entity;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author 朱伟伟
 * @date 2021-02-14 20:01:56
 * @description
 */
public class PageInfo<E> {
    /**
     * 页码，从1开始
     */
    private int pageNum;
    /**
     * 页面大小
     */
    private int pageSize;
    /**
     * 总数
     */
    private long total;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 结果
     */
    private List<E> result;

    public PageInfo() {
    }

    public PageInfo(List<E> data) {
        if (data instanceof Page) {
            Page<E> page = (Page<E>) data;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
            this.pages = page.getPages();
            this.result = page.getResult();
        } else {
            this.pageNum = 1;
            this.pageSize = data.size();
            this.total = data.size();
            this.pages = 1;
            this.result = data;
        }
    }

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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<E> getResult() {
        return result;
    }

    public void setResult(List<E> result) {
        this.result = result;
    }
}
