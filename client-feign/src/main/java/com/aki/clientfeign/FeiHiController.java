package com.aki.clientfeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FeiHiController {
    @Autowired
    FeiHiService hiService;
    @Value("${server.port}")
    String port;


    //正常的 controller 访问方式
    @RequestMapping("/hello-client")
    public String sayHello(@RequestParam(value = "name") String name) {
        StringBuffer buffer = new StringBuffer();
//        name = port + name;
        buffer.append(name);
        //调用了远程的微服务
        String word = hiService.sayHi(buffer.toString());
        return word;
    }


    @RequestMapping(value = "test2")
    public void test2(HttpServletResponse response) throws IOException {
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition", "attachment;fileName=" + "aaaa");// 设
        String a = hiService.test2();
        byte[] decoded = Base64.getDecoder().decode(a);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(decoded);
    }

    @RequestMapping(value = "testmap")
    public String testmap() {
        Map map = new HashMap();
        map.put("aaa",123);
        String a =  hiService.testmap(map);
        return a;
    }
}
