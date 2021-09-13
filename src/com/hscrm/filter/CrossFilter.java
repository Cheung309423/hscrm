package com.hscrm.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Peter Cheung
 * @user PerCheung
 * @date 2021/9/6 20:25
 */
@WebFilter("/*")
public class CrossFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //处理跨域：授权，谁可以访问我
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //允许的访问源
        response.setHeader("Access-Control-Allow-Origin", "*");
        //允许的请求方式：GET、POST
        response.setHeader("Access-Control-Allow-Methods", "*");
        //用来指定本次预检请求的有效期，单位为秒
        response.setHeader("Access-Control-Max-Age", "3600");
        //请求头中必须包含的键值对
        response.setHeader("Access-Control-Allow-Headers", "Origin,Accept,Content-Type,token");
        response.setHeader("Access-Control-Expose-Headers", "token");
        //允许访问的凭证是否作为相应的一部分
        response.setHeader("Access-Control-Allow-Credentials", "true");
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
