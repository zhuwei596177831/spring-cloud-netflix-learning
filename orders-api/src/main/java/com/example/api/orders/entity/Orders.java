package com.example.api.orders.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.base.api.entity.BaseEntity;

import java.time.LocalDateTime;

/**
 * @author 朱伟伟
 * @date 2021-02-14 16:36:23
 * @description
 */
@TableName("orders")
public class Orders extends BaseEntity {

    private static final long serialVersionUID = -3118764263389190657L;

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
