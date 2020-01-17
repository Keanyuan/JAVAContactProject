package com.anjiplus.mybatis.pojo;

import java.util.List;

/**
 * @Auther: kean_qi
 * @Date: 2020/1/16 16:47
 * @Description:
 */
public class PageInfo {
    //每页个数
    private int pageSize = 10;
    //第几页
    private int pageNumber = 1;
    //总数
    private long total;
    //数据
    private List<?> list;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
