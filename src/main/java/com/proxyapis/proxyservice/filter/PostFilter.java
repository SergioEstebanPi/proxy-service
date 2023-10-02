package com.proxyapis.proxyservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.proxyapis.proxyservice.config.Context;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class PostFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(PostFilter.class);

    @Override
    public String filterType() { return "post"; }

    @Override
    public int filterOrder() { return 0; }

    @Override
    public boolean shouldFilter() { return true; }

    @Override
    public Object run() throws ZuulException {
        final Context context = Context.getInstance();
        HttpServletResponse httpServletResponse = RequestContext.getCurrentContext().getResponse();
        logger.info("PostFilter " + httpServletResponse.getStatus());
        return null;
    }
}
