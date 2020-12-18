package com.aki.clientfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

//服务名
@FeignClient(value = "service-name", fallbackFactory = MyFallBackFactory.class)//降级 不可用的备用方案
public interface FeiHiService {
    //服务方法名
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String sayHi(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/test2")
    String test2();

    @RequestMapping(value = "/testmap", consumes = "application/json")
    String testmap(@RequestBody Map map);
}
