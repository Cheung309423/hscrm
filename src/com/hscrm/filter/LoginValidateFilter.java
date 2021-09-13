package com.hscrm.filter;

import com.hscrm.util.JsonUtil;
import com.hscrm.util.Token;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Peter Cheung
 * @user PerCheung
 * @date 2021/9/6 20:42
 */
@WebFilter("/user/*")
public class LoginValidateFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String tokenString = req.getHeader("token");
        Token token = null;
        if (tokenString != null) {
            token = (Token) req.getServletContext().getAttribute(tokenString);
        }

        if (token == null || token.getLogin() == false) {
            //没有登录
            PrintWriter out = resp.getWriter();
            //json
            JsonUtil<String> json = new JsonUtil<>();

            String responseString = json.toString(1001, "登陆失败", "");

            out.write(responseString);

            out.close();
        }
        filterChain.doFilter(req, resp);

    }

    @Override
    public void destroy() {
    }
}
