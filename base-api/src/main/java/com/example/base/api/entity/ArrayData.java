package com.example.base.api.entity;

import java.util.Collection;

/**
 * @author 朱伟伟
 * @date 2020-12-27 12:02:17
 * @description
 */
public class ArrayData<T> {
    private Collection<T> data;
    private long total;

    public ArrayData(Collection<T> data, long total) {
        this.data = data;
        this.total = total;
    }

    public Collection<T> getData() {
        return data;
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

}
