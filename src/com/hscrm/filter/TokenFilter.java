package com.hscrm.filter;

import com.hscrm.util.Token;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Peter Cheung
 * @user PerCheung
 * @date 2021/9/10 11:58
 */
@WebFilter("/*")
public class TokenFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //会话跟踪和验证
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        ServletContext context = req.getServletContext();

        //获取请求头中的token
        String tokenString = req.getHeader("token");
        String respToken = "";
        Token token = null;
        if (tokenString == null) {
            //第一次生成token
            token = new Token();
            token.setUuid(UUID.randomUUID().toString());
            context.setAttribute(token.getUuid(), token);

        } else {
            //验证是否过期
            token = (Token) context.getAttribute(tokenString);
            if (token == null) {
                //再次次生成token
                token = new Token();
                token.setUuid(UUID.randomUUID().toString());
                context.setAttribute(token.getUuid(), token);
            }
            respToken = token.getUuid();
        }
        req.setAttribute("token", respToken);
        resp.setHeader("token", respToken);
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}
