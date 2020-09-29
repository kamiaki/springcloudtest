package com.aki.zuultest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZuultestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuultestApplication.class, args);
    }

}