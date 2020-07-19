package com.aki.clientfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//服务名
@FeignClient(value = "service-name", fallbackFactory = MyFallBackFactory.class)//降级 不可用的备用方案
public interface FeiHiService {
    //服务方法名
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String sayHi(@RequestParam(value = "name") String name);
}
