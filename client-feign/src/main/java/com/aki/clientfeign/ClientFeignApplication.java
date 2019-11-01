package com.aki.clientfeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class ClientFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientFeignApplication.class, args);
    }

    @FeignClient(value = "service-hello1")
    public interface FeiHiService {
        @RequestMapping(value = "/hello",method = RequestMethod.GET)
        String sayHi(@RequestParam(value = "name") String name);
    }

    @RestController
    public class FeiHiController {
        @Autowired
        FeiHiService hiService;
        @RequestMapping("/hello-client")
        public String sayHello(@RequestParam(value = "name") String name){
            StringBuffer buffer = new StringBuffer();
            buffer.append("名字是:").append(name);
            String word = hiService.sayHi(buffer.toString());
            return word;
        }
    }
}




