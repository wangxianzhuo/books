package com.github.xianzhuo.library.common;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * system log filter.
 *
 * @author chenye
 * @author Yang XuePing
 */
public class SystemLogFilter implements Filter {
    private ApplicationContext context;
//
//    public static String getClientIp(HttpServletRequest request) {
//        String ip = request.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
//            ip = request.getRemoteAddr();
//        }
//        return ip;
//    }
//
//    public static String getUserId(HttpServletRequest request) {
//        String userId = SessionUtils.currentUserId(request);
//        if (userId != null) {
//            return userId;
//        }
//        return "-";
//    }
//
//    public static String getReferer(HttpServletRequest request) {
//        String referer = request.getHeader("referer");
//        return referer == null ? "-" : referer;
//    }
//
//    public static String getUserAgent(HttpServletRequest request) {
//        String ua = request.getHeader("User-Agent");
//        return ua == null ? "-" : ua;
//    }
//
//    public static String getRequest(HttpServletRequest request) {
//        return request.getMethod() + " " + request.getRequestURI();
//    }
//
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = WebApplicationContextUtils
                .getWebApplicationContext(filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        String requestPath = request.getRequestURI().substring(
//                request.getContextPath().length());
//        if (requestPath.startsWith("/static/")
//                || requestPath.equals("/login")
//                || requestPath.equals("/no_permission")) {
//            filterChain.doFilter(servletRequest, servletResponse);
//            return;
//        }
//        SystemLogService systemLogService = context.getBean(SystemLogService.class);
//        systemLogService.sendToKafka(getClientIp(request), getUserId(request), getReferer(request), System.currentTimeMillis(), request.getRequestURI(), ResourceMapping.RequestMethod.valueOf(request.getMethod()));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}