package com.hscrm.controller;

import com.hscrm.service.EmpService;
import com.hscrm.service.impl.EmpServiceImpl;
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
 * @date 2021/9/7 10:53
 */
@WebServlet("/usernameUnique")
public class UsernameUniqueServlet extends HttpServlet {
    private EmpService empService = new EmpServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String username = req.getParameter("username");

        //唯一性判断
        int i = empService.usernameUnique(username);

        //响应
        JsonUtil<Integer> json = new JsonUtil<>();
        String responseString="";
        if (i == 0) {
            responseString = json.toString(1000, "OK", i);
        } else {
            responseString = json.toString(1003, "用户名重复", i);
        }

        PrintWriter out = resp.getWriter();
        out.write(responseString);
        out.close();
    }
}
