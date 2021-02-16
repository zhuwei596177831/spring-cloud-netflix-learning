package com.example.openfeign.learning.controller;

import com.example.api.goods.entity.Goods;
import com.example.api.orders.entity.Orders;
import com.example.base.api.entity.ArrayData;
import com.example.base.api.entity.Result;
import com.example.openfeign.learning.feignclient.FeignRibbonClient;
import com.example.openfeign.learning.feignclient.GoodsOpenFeignClient;
import com.example.openfeign.learning.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2021-02-14 16:38:24
 * @description
 */
@RestController
public class OrdersController {
    @Autowired
    OrdersService ordersService;
    @Autowired
    GoodsOpenFeignClient goodsOpenFeignClient;
    @Autowired
    FeignRibbonClient feignRibbonClient;

    @GetMapping("/findOrdersPageList")
    public Result<ArrayData<Orders>> findPageList(@RequestParam Integer pageNum,
                                                  @RequestParam Integer pageSize,
                                                  HttpServletRequest httpServletRequest) {
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            System.out.println(header + ":" + httpServletRequest.getHeader(header));
        }
        return ordersService.findPageList(pageNum, pageSize);
    }

    /**
     * @param pageNum:
     * @param pageSize:
     * @author: 朱伟伟
     * @date: 2021-02-14 18:17
     * @description: feign方式rest调用测试
     **/
    @GetMapping("/findGoodsPageListByOpenFeign")
    public Result<ArrayData<Goods>> findGoodsPageListByOpenFeign(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        return Result.ok(goodsOpenFeignClient.getGoodsPageListBySpringQueryMap(map));
//        return Result.ok(goodsOpenFeignClient.getGoodsPageList(pageNum, pageSize));
    }

    @GetMapping("/getGoodsListByCondition")
    public Result<ArrayData<Goods>> getGoodsListByCondition() {
        Goods goods = new Goods();
        goods.setName("颠三倒四");
        return Result.ok(goodsOpenFeignClient.getGoodsListByCondition(goods));
    }

    @GetMapping("/getGoodsListByConditionMap")
    public Result<ArrayData<Goods>> getGoodsListByConditionMap() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "的分别打分");
        return Result.ok(goodsOpenFeignClient.getGoodsListByConditionMap(map));
    }

    /**
     * @author: 朱伟伟
     * @date: 2021-02-14 18:18
     * @description: feign是否可以自动负载均衡测试
     **/
    @GetMapping("/getResult")
    public Result result() {
        return feignRibbonClient.getFeignRibbonResult();
    }


//    @PostConstruct
//    public void init() {
//        ordersService.batchInsert();
//    }


}
