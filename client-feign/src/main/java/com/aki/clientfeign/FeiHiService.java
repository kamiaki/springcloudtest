package com.aki.clientfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-hello1")
public interface FeiHiService {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    String sayHi(@RequestParam(value = "name") String name);
}
