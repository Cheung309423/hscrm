package com.hscrm.controller;

import com.hscrm.util.JsonUtil;
import com.hscrm.util.Token;

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
 * @date 2021/9/6 23:56
 */
@WebServlet("/user/getUsername")
public class GetUsernameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tokenString = (String) req.getAttribute("token");
        Token token = (Token) req.getServletContext().getAttribute(tokenString);
        String sUsername = token.getUsername();
        JsonUtil<String> json = new JsonUtil<>();
        String ok = json.toString(1000, "OK", sUsername);
        PrintWriter out = resp.getWriter();
        out.write(ok);
        out.close();
    }
}
