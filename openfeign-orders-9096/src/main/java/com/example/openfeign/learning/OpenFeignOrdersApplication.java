package com.example.openfeign.learning;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author 朱伟伟
 * @date 2021-02-12 13:09:41
 * @description
 */
@SpringBootApplication(scanBasePackages = {"com.example"})
@MapperScan(basePackages = {"com.example.openfeign.learning.mapper"})
//开启openfeign继续接口方式的rest调用
@EnableFeignClients(basePackages = {"com.example.openfeign.learning.feignclient"})
public class OpenFeignOrdersApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(OpenFeignOrdersApplication.class).web(WebApplicationType.SERVLET).run(args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

}
