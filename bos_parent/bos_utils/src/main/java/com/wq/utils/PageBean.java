package com.wq.utils;

/*
    PageBean工具类
*/

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class PageBean {


    private int currentPage;    //当前页数
    private int total;          //总记录数
    private int pageSize;       //每页大小
    private List<T> rows;       //每页显示数据集合
    private DetachedCriteria detachedCriteria;      //查询条件

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }

    public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
    }
}
