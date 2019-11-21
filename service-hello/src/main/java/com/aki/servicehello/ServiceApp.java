package com.aki.servicehello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@RestController
public class ServiceApp {
    @Value("${server.port}")
    String port;

    //服务方法名
    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "name") String name) { //如果参数为实体类用: @RequestBody  1.如果直接 访问时需使用application/json 格式访问
        //2.如果通过问服务调用,则直接传入类即可
        return "hi " + name+"   "+port;
    }
}
