package com.example.hystrix.learning.controller;

import com.example.api.goods.entity.Goods;
import com.example.base.api.entity.ArrayData;
import com.example.base.api.entity.PageInfo;
import com.example.base.api.entity.Result;
import com.example.base.api.entity.ResultCode;
import com.example.hystrix.learning.service.GoodsService;
import com.github.pagehelper.Page;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2021-02-12 13:18:09
 * @description
 */
@RestController
public class GoodsController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
    @Autowired
    GoodsService goodsService;
    /**
     * @description: zuul路由后无法注入
     **/
//    @Autowired
//    HttpServletRequest httpServletRequest;

    /**
     * @param pageNum:
     * @param pageSize:
     * @author: 朱伟伟
     * @date: 2021-02-12 16:29
     * @description:
     * @see com.netflix.hystrix.HystrixCommandProperties
     * @see com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy
     * @see com.netflix.hystrix.HystrixThreadPoolProperties
     **/
    @GetMapping("/findGoodsPageList")
    @HystrixCommand(
            fallbackMethod = "findPageListFallBack",
            commandProperties = {
                    //线程隔离(Isolation)相关：
                    //配置请求隔离的方式，有 threadPool（线程池，默认）和 semaphore（信号量）两种
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    //默认值是 10，此配置项要在 execution.isolation.strategy 配置为 semaphore 时才会生效，
                    // 它指定了一个 Hystrix 方法使用信号量隔离时的最大并发数，超过此并发数的请求会被拒绝。
                    // 信号量隔离的配置就这么一个，信号量隔离配置不灵活
//                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "10"),
//                    @HystrixProperty(name = "requestCache.enabled", value = "false")
                    //是否给方法执行设置超时，默认为 true。
//                    @HystrixProperty(name = "execution.timeout.enabled", value = "true"),
                    //方法执行超时时间，默认值是 1000，即 1秒
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            }
            //线程池配置 线程池方式是继续java线程池方式实现的 无法精确控制QPS
//            threadPoolProperties = {
            //核心线程池的大小，默认值是 10，一般根据 QPS * 99% cost + redundancy count 计算得出。
//                    @HystrixProperty(name = "coreSize", value = "10")
            //是否允许线程池扩展到最大线程池数量，默认为 false;
//                    @HystrixProperty(name = "allowMaximumSizeToDivergeFromCoreSize", value = "false"),
            //线程池中线程的最大数量，默认值是 10，此配置项单独配置时并不会生效，需要启用 allowMaximumSizeToDivergeFromCoreSize 项。
//                    @HystrixProperty(name = "maximumSize", value = "10"),
            //作业队列的最大值，默认值为 -1，设置为此值时，队列会使用 SynchronousQueue，
            // 此时其 size 为0，Hystrix 不会向队列内存放作业。如果此值设置为一个正的 int 型，
            // 队列会使用一个固定 size 的 LinkedBlockingQueue，此时在核心线程池内的线程都在忙碌时，
            // 会将作业暂时存放在此队列内，但超出此队列的请求依然会被拒绝。
//                    @HystrixProperty(name = "maxQueueSize", value = "-1"),
            //由于 maxQueueSize 值在线程池被创建后就固定了大小，如果需要动态修改队列长度的话可以设置此值，
            // 即使队列未满，队列内作业达到此值时同样会拒绝请求。此值默认是 5，所以有时候只设置了 maxQueueSize 也不会起作用。
//                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "5"),
            //由上面的 maximumSize，我们知道，线程池内核心线程数目都在忙碌，再有新的请求到达时，
            // 线程池容量可以被扩充为到最大数量，等到线程池空闲后，多于核心数量的线程还会被回收，
            // 此值指定了线程被回收前的存活时间，默认为 1，即两分钟。
//                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "1")
//            }
    )
    public Result<ArrayData<Goods>> findPageList(@RequestParam Integer pageNum,
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
        logger.info("开始查询hystrix_Test信息");
        return goodsService.findPageList(pageNum, pageSize);
    }

    public Result<ArrayData<Goods>> findPageListFallBack(Integer pageNum, Integer pageSize, HttpServletRequest httpServletRequest) {
        logger.info("findPageList被限流:{},{}", pageNum, pageSize);
        return ResultCode.COMMON.getResult("findPageList被限流");
    }

    @GetMapping("/insertGood")
    @HystrixCommand(fallbackMethod = "insertFallBack")
    public Result insert() {
        return goodsService.insert();
    }

    public Result insertFallBack() {
        logger.info("insertFallBack被限流");
        return ResultCode.COMMON.getResult("insertFallBack被限流");
    }


//    @PostConstruct
//    public void init() {
//        goodsService.batchInsert();
//    }

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/getResult")
    public Result getResult() {
//        return restTemplate.getForObject("http://127.0.0.1:9093/eureka-client/getResult", Result.class);
        return restTemplate.getForObject("http://eureka-client-ribbon/eureka-client/getResult", Result.class);
    }

    @GetMapping("/getGoodsPageList")
    public PageInfo<Goods> getGoodsPageList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Goods> goods = goodsService.getGoodsPageList(pageNum, pageSize);
        return new PageInfo<>(goods);
    }

    @GetMapping("/getGoodsListByCondition")
    public List<Goods> getGoodsListByCondition(Goods goods) {
        System.out.println("goods:" + goods);
        return goodsService.getGoodsListByCondition(goods);
    }

    @GetMapping("/getGoodsListByConditionMap")
    public List<Goods> getGoodsListByConditionMap(Map<String, String> map) {
        System.out.println("map:" + map);
        return goodsService.getGoodsListByConditionMap(map);
    }


}
