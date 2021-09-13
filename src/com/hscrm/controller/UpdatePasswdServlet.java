package com.hscrm.controller;

import com.hscrm.service.EmpService;
import com.hscrm.service.impl.EmpServiceImpl;
import com.hscrm.util.JsonUtil;
import com.hscrm.util.MD5Util;
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
 * @date 2021/9/7 8:33
 */
@WebServlet("/user/UpdatePasswdServlet")
public class UpdatePasswdServlet extends HttpServlet {
    private EmpService empService = new EmpServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        //String username = req.getParameter("username");
        String tokenString = (String) req.getAttribute("token");
        Token token = (Token) req.getServletContext().getAttribute(tokenString);
        String username = token.getUsername();
        String oldPasswd = req.getParameter("oldPasswd");
        String newPasswd = req.getParameter("newPasswd");

        //修改密码
        int i = empService.updatePasswd(username, MD5Util.toMd5(oldPasswd), MD5Util.toMd5(newPasswd));

        JsonUtil<String> json = new JsonUtil<>();
        String responseString = "";
        if (i == -1) {
            //与老密码相同
            responseString = json.toString(1005, "操作失败", "旧密码错误");
        } else if (i == 0) {
            //修改失败
            responseString = json.toString(1005, "操作失败", "旧密码错误");
        } else if (i == 1) {
            //修改成功
            responseString = json.toString(1000, "OK", "修改成功");
        }
        PrintWriter out = resp.getWriter();
        out.write(responseString);
        out.close();
    }
}
