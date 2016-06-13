package com.maybe.live.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Tate
 * @date: 2016/5/11 20:07
 */
@Component
public class HttpFilter implements Filter {

    private final static Logger log = LoggerFactory.getLogger(RequestContext.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("HttpFilter.init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        try {
            RequestContext.init(null, req, resp);
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            if (RequestContext.get() != null) {
                RequestContext.get().destroy();
            }
        }

    }

    @Override
    public void destroy() {
        log.debug("HttpFilter.destroy");
    }
}
