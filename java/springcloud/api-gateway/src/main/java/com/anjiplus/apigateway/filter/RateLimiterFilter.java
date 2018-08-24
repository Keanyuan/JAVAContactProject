package com.anjiplus.apigateway.filter;

import com.anjiplus.apigateway.exception.RateLimiterException;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/24 17:17
 * @Description: 限流连接器
 */
@Component
public class RateLimiterFilter extends ZuulFilter {

    //每秒放100个
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
//        设置优先级
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //如果没有拿到令牌
        if (!RATE_LIMITER.tryAcquire()) {
            throw new RateLimiterException();
        }

        return null;
    }
}
