package com.hscrm.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author Peter Cheung
 * @user PerCheung
 * @date 2021/9/6 20:59
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //POST
        servletRequest.setCharacterEncoding("utf-8");
        //响应
        servletResponse.setContentType("application/json;charset=utf-8");

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
    }
}
