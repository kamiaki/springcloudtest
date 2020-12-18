package com.aki.clientfeign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class MyFallBackFactory implements FallbackFactory<FeiHiService> {
    @Override
    public FeiHiService create(Throwable throwable) {
        return new FeiHiService() {
            @Override
            public String sayHi(String name) {
                return null;
            }

            @Override
            public String test2() {
                return null;
            }

            @Override
            public String testmap(Map map) {
                return null;
            }
        };
    }
}
