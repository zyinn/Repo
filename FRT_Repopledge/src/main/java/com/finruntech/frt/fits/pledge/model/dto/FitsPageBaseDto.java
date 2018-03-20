package com.finruntech.frt.fits.pledge.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yinan.zhang on 2018/1/3.
 */
public class FitsPageBaseDto {
    private String orderBy;
    private String orderColumn;
    private int pageNum;
    private int pageSize;

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public int getPageNum() {
        return this.pageNum/this.pageSize + 1;
    }

    public int getPageSize() {
        return pageSize;
    }
}
