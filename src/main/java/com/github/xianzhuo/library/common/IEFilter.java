package com.github.xianzhuo.library.common;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * IE check filter.
 *
 * @author Yang XuePing
 */
public class IEFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String agent = request.getHeader("User-Agent");
        if (agent != null) {
            if (agent.indexOf("MSIE 6.0") > 0 || agent.indexOf("MSIE 7.0") > 0 || agent.indexOf("MSIE 8.0") > 0 || agent.indexOf("MSIE 9.0") > 0) {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                request.getRequestDispatcher("WEB-INF/views/common/browser_not_supported.jsp").forward(
                        request, response);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
