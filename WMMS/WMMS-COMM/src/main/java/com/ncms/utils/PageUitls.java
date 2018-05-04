package com.ncms.utils;

import com.github.pagehelper.Page;

/**
 * 分页实体用户返回数据
 * cjw 2017年8月23日14:15:46
 * 643969814@qq.com
 * @param <T>
 */
public class PageUitls<T>{
    public PageUitls(Page<T> page){
        this.list = page;
        this.total = page.getTotal();
        this.pages = page.getPages();
        this.currPage = page.getPageNum();
        this.pageSize = page.getPageSize();
    }
    private Page<T> list;

    private Long total;

    private Integer pages;

    private Integer currPage;

    private int pageSize;

    public Page<T> getList() {
        return list;
    }

    public void setList(Page<T> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
