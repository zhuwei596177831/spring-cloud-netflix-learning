package com.example.openfeign.learning.feignclient;

import com.example.api.goods.entity.Goods;
import com.example.base.api.entity.PageInfo;
import com.example.openfeign.learning.feignclient.fallback.GoodsClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2021-02-14 16:58:33
 * @description feign调用goods rest服务
 */
//@FeignClient(name = "hystrix-goods-9095", fallback = GoodsClientFallback.class, path = "/goods")
@FeignClient(name = "hystrix-goods-9095", path = "/goods")
public interface GoodsOpenFeignClient {

    @GetMapping("/getGoodsPageList")
    PageInfo<Goods> getGoodsPageList(@RequestParam Integer pageNum, @RequestParam Integer pageSize);

    /**
     * @param queryMap:
     * @author: 朱伟伟
     * @date: 2021-02-15 14:05
     * @description: @SpringQueryMap将pojo或map映射成param
     **/
    @GetMapping("/getGoodsPageList")
    PageInfo<Goods> getGoodsPageListBySpringQueryMap(@SpringQueryMap Map<String, Integer> queryMap);

    /**
     * @param goods:
     * @author: 朱伟伟
     * @date: 2021-02-15 14:19
     * @description: @SpringQueryMap直接传递pojo
     **/
    @GetMapping("/getGoodsListByCondition")
    List<Goods> getGoodsListByCondition(@SpringQueryMap Goods goods);

    /**
     * @param queryMap:
     * @author: 朱伟伟
     * @date: 2021-02-15 14:19
     * @description: @SpringQueryMap直接传递map (服务端无法接受  map列表为空)
     **/
    @GetMapping("/getGoodsListByConditionMap")
    List<Goods> getGoodsListByConditionMap(@SpringQueryMap Map<String, String> queryMap);

}
