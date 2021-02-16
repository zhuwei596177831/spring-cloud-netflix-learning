package com.example.eurekaclient;

import com.example.base.api.entity.Result;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2021-02-11 19:22:26
 * @description
 */
@SpringBootApplication(scanBasePackages = {"com.example"})
@RestController
public class EurekaClientRibbon02Application {

    @Autowired
    EurekaClient eurekaClient;

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientRibbon02Application.class, args);
    }

    @GetMapping("/getEurekaClient")
    public InstanceInfo getEurekaClient() {
        return eurekaClient.getNextServerFromEureka("EUREKA-CLIENT", false);
    }

    @GetMapping("/getResult")
    public Result getResult() {
        Map<String, Object> map = new LinkedHashMap<>(4);
        map.put("time", LocalDateTime.now());
        map.put("name", "eureka-client-ribbon02");
        return Result.ok(map);
    }


}
