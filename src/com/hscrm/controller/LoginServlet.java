package com.hscrm.controller;

import com.hscrm.service.EmpService;
import com.hscrm.service.impl.EmpServiceImpl;
import com.hscrm.util.JsonUtil;
import com.hscrm.util.MD5Util;
import com.hscrm.util.Token;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Peter Cheung
 * @user PerCheung
 * @date 2021/9/6 23:12
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private EmpService empService = new EmpServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String username = req.getParameter("username");
        String passwd = req.getParameter("passwd");

        //数据校验

        //登陆验证
        int i = empService.login(username, MD5Util.toMd5(passwd));
        ServletContext context = req.getServletContext();
        String tokenString = null;
        Token token = null;

        if (i == 2) {
            tokenString = (String) req.getAttribute("token");
            token = (Token) context.getAttribute(tokenString);
            token.setLogin(true);
            token.setUsername(username);
        }
        //响应json
        JsonUtil<Integer> json = new JsonUtil<>();
        String s = json.toString(1000, "OK", i);
        PrintWriter out = resp.getWriter();
        out.write(s);
        out.close();
    }
}
