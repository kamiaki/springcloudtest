package com.aki.servicehello;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    @HystrixCommand(fallbackMethod = "errorMsg")//出问题熔断去找谁
    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "name") String name) { //如果参数为实体类用: @RequestBody  1.如果直接 访问时需使用application/json 格式访问
        //        //模仿超时异常，熔断
        if ("aaaa".equals(name) || "bbbb".equals(name)){
            System.out.println("熔断熔断熔断熔断");
            System.out.println("熔断熔断熔断熔断");
            throw new RuntimeException("熔断异常的内容"); //抛出熔断异常
        }
        //2.如果通过问服务调用,则直接传入类即可
        return "hi " + name+"   "+port;
    }

    public String errorMsg(@RequestParam(value = "name") String name, Throwable exception){
        if ("bbbb".equals(name)){
            System.out.println("客户端降级降级降级降级降级");
            System.out.println("客户端降级降级降级降级降级");
            throw new RuntimeException(); // 客户端降级了
        }
        return "容断了,入参:" + name + "异常是:" + exception.getMessage();
    }
}
