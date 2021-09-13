package com.hscrm.controller;

import com.hscrm.domain.Emp;
import com.hscrm.service.EmpService;
import com.hscrm.service.impl.EmpServiceImpl;
import com.hscrm.util.JsonUtil;
import com.hscrm.util.MD5Util;

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
 * @date 2021/9/6 22:14
 */
@WebServlet("/reg")
public class RegEmpServlet extends HttpServlet {
    private EmpService empService = new EmpServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //相应json对象
        JsonUtil<String> json = new JsonUtil<>();
        PrintWriter out = resp.getWriter();
        boolean validateFlag = true;

        //获取请求参数
        String e_name = req.getParameter("e_name");
        String e_sex = req.getParameter("e_sex");
        String e_tel = req.getParameter("e_tel");
        String username = req.getParameter("username");
        String passwd = req.getParameter("passwd");
        Emp emp = new Emp(e_name, e_sex, e_tel, username, MD5Util.toMd5(passwd));
        //String verifyCode = req.getParameter("verifyCode");

        //验证码校验
        // HttpSession session = req.getSession();
        // String verifyCodeInSession = (String) session.getAttribute("verifyCode");
        // if (verifyCode == null || verifyCode.trim().equalsIgnoreCase("") || !verifyCode.equalsIgnoreCase(verifyCodeInSession)) {
        //     String responseString = json.toString(1006, "验证码错误", "");
        //     out.write(responseString);
        //     out.close();
        //     return;
        // }

        //数据校验
        //姓名必填
        // if ((e_name == null || e_name.trim().equals(""))) {
        //     validateFlag = false;
        // } else {
        //     //格式
        //     String s = "^[\u4e00-\u9fa5]{2,4}$";
        //     if (!Pattern.matches(s, e_name)) {
        //         validateFlag = false;
        //     }
        // }

        //性别校验
        if (e_sex != null && e_sex.trim().length() > 0) {
            if (!(e_sex.trim().equals("男") || e_sex.trim().equals("女"))) {
                validateFlag = false;
            }
        }

        if (!validateFlag) {
            String responseString = json.toString(1003, "参数异常", "");
            out.write(responseString);
            out.close();
            return;
        }


        //判断用户名唯一性
        int unique = empService.usernameUnique(username);
        if (unique != 0) {
            String responseString = json.toString(1002, "用户名不唯一", "");
            out.write(responseString);
            out.close();
            return;
        }

        //注册
        int i = empService.register(emp);

        //相应json
        String responseString;
        if (i > 0) {
            responseString = json.toString(1000, "OK", "");
        } else {
            responseString = json.toString(1004, "注册失败", "");
        }
        out.write(responseString);
        out.close();
    }
}
