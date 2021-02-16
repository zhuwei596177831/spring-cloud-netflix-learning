package com.example.openfeign.learning.feignclient.fallback;

import com.example.api.goods.entity.Goods;
import com.example.base.api.entity.PageInfo;
import com.example.openfeign.learning.feignclient.GoodsOpenFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2021-02-14 20:44:57
 * @description
 */
@Component
public class GoodsClientFallback implements GoodsOpenFeignClient {

    Logger LOGGER = LoggerFactory.getLogger(GoodsClientFallback.class);

    @Override
    public PageInfo<Goods> getGoodsPageList(Integer pageNum, Integer pageSize) {
        LOGGER.error("getGoodsPageList执行fallback......");
//        throw new RuntimeException("getGoodsPageList执行fallback");
        return null;
    }

    @Override
    public PageInfo<Goods> getGoodsPageListBySpringQueryMap(Map<String, Integer> queryMap) {
        LOGGER.error("getGoodsPageListBySpringQueryMap执行fallback......");
        return null;
    }

    @Override
    public List<Goods> getGoodsListByCondition(Goods goods) {
        LOGGER.error("getGoodsListByCondition执行fallback......");
        return null;
    }

    @Override
    public List<Goods> getGoodsListByConditionMap(Map<String, String> queryMap) {
        return null;
    }

}
