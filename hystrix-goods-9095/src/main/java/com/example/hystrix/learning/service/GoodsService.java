package com.example.hystrix.learning.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.api.goods.entity.Goods;
import com.example.base.api.entity.ArrayData;
import com.example.base.api.entity.Result;
import com.example.hystrix.learning.mapper.GoodsMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author 朱伟伟
 * @date 2021-02-12 13:53:29
 * @description
 */
@Service
public class GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Transactional
    public void batchInsert() {
        for (int i = 0; i < 100; i++) {
            Goods hystrixTest = new Goods();
            String string = UUID.randomUUID().toString();
            hystrixTest.setName(string);
            goodsMapper.insert(hystrixTest);
        }
    }

    public Result<ArrayData<Goods>> findPageList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> data = goodsMapper.selectList(new QueryWrapper<>());
        return Result.ok(data);
    }

    @Transactional
    public Result insert() {
        Goods hystrixTest = new Goods();
        hystrixTest.setName("朱伟伟");
        goodsMapper.insert(hystrixTest);
        System.out.println(1 / 0);
        return Result.ok();
    }

    public List<Goods> getGoodsPageList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return goodsMapper.selectList(new QueryWrapper<>());
    }

    public List<Goods> getGoodsListByCondition(Goods goods) {
        return goodsMapper.selectList(new QueryWrapper<>());
    }

    public List<Goods> getGoodsListByConditionMap(Map<String, String> map) {
        return goodsMapper.selectList(new QueryWrapper<>());
    }
}
