package com.example.openfeign.learning.feignclient;

import com.example.base.api.entity.Result;
import com.example.openfeign.learning.feignclient.ribbonconfig.FeignRibbonClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 朱伟伟
 * @date 2021-02-14 18:15:30
 * @description
 */
@FeignClient(name = "eureka-client-ribbon", configuration = FeignRibbonClientConfig.class)
public interface FeignRibbonClient {

    @GetMapping("/eureka-client/getResult")
    Result getFeignRibbonResult();

}
