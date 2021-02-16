package com.example.zuul.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author 朱伟伟
 * @date 2021-02-16 11:32:39
 * @description
 */
@SpringBootApplication
@EnableZuulProxy
public class Zuul9090Application {

    public static void main(String[] args) {
        SpringApplication.run(Zuul9090Application.class, args);
    }

}
