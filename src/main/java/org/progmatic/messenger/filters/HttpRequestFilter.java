package org.progmatic.messenger.filters;

import org.progmatic.messenger.controllers.MessageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class HttpRequestFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("valami");
        servletRequest.getParameterNames();
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
