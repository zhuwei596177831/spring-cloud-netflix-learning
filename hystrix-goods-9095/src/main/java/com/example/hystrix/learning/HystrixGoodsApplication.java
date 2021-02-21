package com.example.hystrix.learning;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import irule.EurekaClientRibbonConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author 朱伟伟
 * @date 2021-02-12 13:09:41
 * @description
 */
@SpringBootApplication(scanBasePackages = {"com.example"})
@MapperScan(basePackages = {"com.example.hystrix.learning.mapper"})
@EnableHystrix
//针对特定服务自定义配置类
@RibbonClients(value = {@RibbonClient(name = "${loadBalancer.clientName}", configuration = EurekaClientRibbonConfig.class)})
public class HystrixGoodsApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(HystrixGoodsApplication.class).web(WebApplicationType.SERVLET).run(args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    /**
     * @author: 朱伟伟
     * @date: 2021-02-14 14:48
     * @description: 配置全局负载均衡  默认轮询 {@link com.netflix.loadbalancer.RoundRobinRule}
     **/
    @Bean
    public IRule iRule() {
        return new RoundRobinRule();
    }

}
