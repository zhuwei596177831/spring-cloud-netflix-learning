package com.example.api.goods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.base.api.entity.BaseEntity;

import java.time.LocalDateTime;

/**
 * @author 朱伟伟
 * @date 2021-02-12 13:47:18
 * @description
 */
@TableName("goods")
public class Goods extends BaseEntity {
    private static final long serialVersionUID = -7138567604765723730L;

    private Long id;

    private String name;

    private LocalDateTime inputTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getInputTime() {
        return inputTime;
    }

    public void setInputTime(LocalDateTime inputTime) {
        this.inputTime = inputTime;
    }
}
