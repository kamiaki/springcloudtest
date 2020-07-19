package com.aki.clientfeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeiHiController {
    @Autowired
    FeiHiService hiService;

    //正常的 controller 访问方式
    @RequestMapping("/hello-client")
    public String sayHello(@RequestParam(value = "name") String name){
        StringBuffer buffer = new StringBuffer();
        buffer.append(name);
        //调用了远程的微服务
        String word = hiService.sayHi(buffer.toString());
        return word;
    }
}
