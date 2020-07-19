package com.aki.zuultest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyZuulFilter extends ZuulFilter {
    private final Logger LOGGER = LoggerFactory.getLogger(MyZuulFilter.class);

    @Override
    public String filterType() {
        return "pre";  //pre之前 post之后
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String name = request.getParameter("name");
        boolean flag = "aaaa".equals(name);
        return flag;
    }

    @Override
    public Object run() throws ZuulException {
        LOGGER.info("shouldFilter = true 要过滤");
        return null;
    }
}
