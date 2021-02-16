package com.example.eurekaserver02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author 朱伟伟
 * @date 2021-02-11 17:55:50
 * @description eureka-server01启动
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer02Application {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer02Application.class, args);
    }

    /**
     * @author: 朱伟伟
     * @date: 2021-02-11 19:43
     * @description: Securing The Eureka Server
     **/
    @EnableWebSecurity
    static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().ignoringAntMatchers("/eureka/**");
            super.configure(http);
        }
    }
}
