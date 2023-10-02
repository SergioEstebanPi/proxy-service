package com.proxyapis.proxyservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.proxyapis.proxyservice.config.Context;
import com.proxyapis.proxyservice.entity.RequestEntity;
import com.proxyapis.proxyservice.repository.RequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
@Slf4j
public class PostFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(PostFilter.class);

    private final RequestRepository repository;

    public PostFilter(RequestRepository repository) {
        this.repository = repository;
    }

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
        HttpServletRequest httpServletRequest = RequestContext.getCurrentContext().getRequest();
        long duration = System.currentTimeMillis() - context.getDuration();
        logger.info("<<[HttpStatusCode: " + httpServletResponse.getStatus() + ", duration: " + duration + "]");
        repository.save(new RequestEntity(httpServletRequest.getRemoteAddr(),
                String.valueOf(httpServletRequest.getRequestURL()),
                httpServletRequest.getMethod(),
                Integer.valueOf(httpServletResponse.getStatus()),
                duration,
                new Date()
        ));
        return null;
    }
}
