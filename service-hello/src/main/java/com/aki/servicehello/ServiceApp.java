package com.aki.servicehello;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.Map;

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


    @RequestMapping(value = "/test2")
    public String test2() {
        byte[] bytes = null;
        try (FileInputStream fileInputStream = new FileInputStream("d:\\test.png")) {
            // 方法1 不推荐 因为可能会丢包
            bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes, 0, fileInputStream.available());
            // 推荐的方法
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            int len;
            byte[] bytes2 = new byte[1024];
            while ((len = fileInputStream.read(bytes2)) != -1) {
                output.write(bytes2, 0, len);
            }
            bytes = output.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String encoded = Base64.getEncoder().encodeToString(bytes);
        return encoded;
    }

    @RequestMapping(value = "/testmap")
    public String testmap(@RequestBody Map map) {
        return map.toString();
    }
}
