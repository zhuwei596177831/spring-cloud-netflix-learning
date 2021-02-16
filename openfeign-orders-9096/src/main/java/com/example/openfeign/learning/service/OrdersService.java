package com.example.openfeign.learning.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.api.orders.entity.Orders;
import com.example.base.api.entity.ArrayData;
import com.example.base.api.entity.Result;
import com.example.openfeign.learning.mapper.OrdersMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author 朱伟伟
 * @date 2021-02-14 16:37:54
 * @description
 */
@Service
public class OrdersService {

    @Autowired
    OrdersMapper ordersMapper;

    public Result<ArrayData<Orders>> findPageList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Orders> data = ordersMapper.selectList(new QueryWrapper<>());
        return Result.ok(data);
    }

    public void batchInsert() {
        for (int i = 0; i < 1000; i++) {
            Orders orders = new Orders();
            String string = UUID.randomUUID().toString();
            orders.setName(string);
            ordersMapper.insert(orders);
        }
    }
}
