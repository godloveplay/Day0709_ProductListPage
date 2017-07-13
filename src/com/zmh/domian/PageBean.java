package com.zmh.domian;

import java.util.List;

/**
 * Created by zmh on 2017/7/13.
 * 分页查询 java bean
 */
public class PageBean {
    private int currPage;
    private int totalCount;
    private int totalPage;
    private int pageSize;
    private List<Product> list;

    public PageBean() {
    }

    public PageBean(int currPage, int totalCount, int totalPage, int pageSize, List<Product> list) {
        this.currPage = currPage;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.pageSize = pageSize;
        this.list = list;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currPage=" + currPage +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                ", list=" + list +
                '}';
    }
}
