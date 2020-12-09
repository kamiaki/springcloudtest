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

    /**
     * 在什么时候过滤
     *  //pre之前 post之后
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 可以写好几个类似这样的过滤器，通过这个数字判断顺序
     * 数字越大，优先级越靠后
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 这里可以写根据什么条件进行过滤
     * 认证成功直接就过去了
     * shouldFilter = true 要过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String name = request.getParameter("name");
        boolean flag = "aaaa".equals(name);
        return flag;
    }

    /**
     * 被拦住了，就来这里喝茶
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        LOGGER.info("shouldFilter = true 要过滤");
        return null;
    }
}
