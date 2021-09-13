package com.hscrm.controller;

import com.hscrm.util.JsonUtil;

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
 * @date 2021/9/6 23:27
 */
@WebServlet("/user/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tokenString = (String) req.getAttribute("token");
        req.getServletContext().removeAttribute(tokenString);

        JsonUtil<Integer> json = new JsonUtil<>();
        String s = json.toString(1000, "OK", 1);
        PrintWriter out = resp.getWriter();
        out.write(s);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
