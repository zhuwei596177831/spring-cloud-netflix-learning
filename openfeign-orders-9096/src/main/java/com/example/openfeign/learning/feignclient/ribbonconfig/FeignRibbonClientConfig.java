package com.example.openfeign.learning.feignclient.ribbonconfig;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 朱伟伟
 * @date 2021-02-14 18:56:33
 * @description
 */
@Configuration
public class FeignRibbonClientConfig {
    @Bean
    public IRule iRule() {
        return new RandomRule();
    }
}
