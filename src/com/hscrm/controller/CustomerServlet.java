package com.hscrm.controller;

import com.hscrm.domain.Customer;
import com.hscrm.service.CustomerService;
import com.hscrm.service.impl.CustomerServiceImpl;
import com.hscrm.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Peter Cheung
 * @user PerCheung
 * @date 2021/9/7 11:38
 */
@WebServlet("/user/customer")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "add":
                add(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            case "findAll":
                findAll(req, resp);
                break;
            case "find":
                find(req, resp);
                break;
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //相应json对象
        JsonUtil<String> json = new JsonUtil<>();
        PrintWriter out = resp.getWriter();
        boolean validateFlag = true;

        //获取请求参数
        String c_name = req.getParameter("c_name");
        String c_sex = req.getParameter("c_sex");
        String c_tel = req.getParameter("c_tel");
        String c_job = req.getParameter("c_job");
        String c_company = req.getParameter("c_company");
        Customer customer = new Customer(c_name, c_sex, c_tel, c_job, c_company);

        //数据校验
        //姓名必填
        if ((c_name == null || c_name.trim().equals(""))) {
            validateFlag = false;
            //} else {
            //    //格式
            //    String s = "^[\u4e00-\u9fa5]{2,4}$";
            //    if (!Pattern.matches(s, c_name)) {
            //        validateFlag = false;
            //    }
        }

        //性别校验
        if (c_sex != null && c_sex.trim().length() > 0) {
            if (!(c_sex.trim().equals("男") || c_sex.trim().equals("女"))) {
                validateFlag = false;
            }
        }

        if (!validateFlag) {
            String responseString = json.toString(1003, "参数异常", "");
            out.write(responseString);
            out.close();
            return;
        }

        //添加
        int i = customerService.addCustomer(customer);

        //相应json
        String responseString;
        if (i > 0) {
            responseString = json.toString(1000, "OK", "");
        } else {
            responseString = json.toString(1004, "添加失败", "");
        }
        out.write(responseString);
        out.close();
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //相应json对象
        JsonUtil<String> json = new JsonUtil<>();
        PrintWriter out = resp.getWriter();

        String c_id = req.getParameter("c_id");
        int i = customerService.deleteCustomer(Integer.parseInt(c_id));

        String responseString = "";
        if (i > 0) {
            responseString = json.toString(1000, "OK", "");
        } else {
            responseString = json.toString(1005, "删除失败", "");
        }
        out.write(responseString);
        out.close();
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //相应json对象
        JsonUtil<String> json = new JsonUtil<>();
        PrintWriter out = resp.getWriter();

        //获取请求参数
        String c_id = req.getParameter("c_id");
        String c_name = req.getParameter("c_name");
        String c_sex = req.getParameter("c_sex");
        String c_tel = req.getParameter("c_tel");
        String c_job = req.getParameter("c_job");
        String c_company = req.getParameter("c_company");
        Customer customer = new Customer(Integer.parseInt(c_id), c_name, c_sex, c_tel, c_job, c_company);

        //修改
        int i = customerService.updateCustomer(customer);

        String responseString = "";
        if (i > 0) {
            responseString = json.toString(1000, "OK", "");
        } else {
            responseString = json.toString(1004, "修改失败", "");
        }
        out.write(responseString);
        out.close();
    }

    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //相应json对象
        JsonUtil<List<Customer>> json = new JsonUtil<>();
        PrintWriter out = resp.getWriter();

        List<Customer> customerList = customerService.findAllCustomer();

        String responseString = "";
        if (customerList != null) {
            responseString = json.toString(1000, "OK", customerList);
        } else {
            responseString = json.toString(1004, "查找失败", null);
        }
        out.write(responseString);
        out.close();
    }

    private void find(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //相应json对象
        JsonUtil<Customer> json = new JsonUtil<>();
        PrintWriter out = resp.getWriter();

        String c_id = req.getParameter("c_id");
        Customer customer = customerService.findCustomer(Integer.parseInt(c_id));

        String responseString = "";
        if (customer != null) {
            responseString = json.toString(1000, "OK", customer);
        } else {
            responseString = json.toString(1004, "查找失败", null);
        }
        out.write(responseString);
        out.close();
    }
}
