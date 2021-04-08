package com.gateway.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义过滤器
 */
public class AccessFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    //过滤器类型
    @Override
    public String filterType() {
        return "pre"; //请求被路由之前举行
    }

    //过滤器的执行顺序
    @Override
    public int filterOrder() {
        return 0;
    }
    //判断过滤器是否需要被执行
    @Override
    public boolean shouldFilter() {
        return true;
    }
    //过滤器的具体逻辑
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info("发送{} request to {}",request.getMethod(),request.getRequestURL().toString());
        String accessToken = request.getParameter("token");
        if(accessToken == null){
            logger.info("token为 null");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        logger.info("token is OK");
        return null;
    }
}
