package com.aki.clientfeign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


@Component
public class MyFallBackFactory implements FallbackFactory<FeiHiService> {
    @Override
    public FeiHiService create(Throwable throwable) {
        return name -> "生产者听了,发生异常了:" + name;
    }
}
