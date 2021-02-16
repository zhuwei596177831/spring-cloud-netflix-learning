package com.example.base.api.entity;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author 朱伟伟
 * @date 2021-02-12 13:48:47
 * @description
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -1080712997142076173L;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
