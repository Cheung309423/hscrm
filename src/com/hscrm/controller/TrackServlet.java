package com.hscrm.controller;

import com.hscrm.domain.Customer;
import com.hscrm.domain.Emp;
import com.hscrm.domain.Track;
import com.hscrm.service.EmpService;
import com.hscrm.service.TrackService;
import com.hscrm.service.impl.EmpServiceImpl;
import com.hscrm.service.impl.TrackServiceImpl;
import com.hscrm.util.JsonUtil;
import com.hscrm.util.Token;

import javax.servlet.ServletContext;
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
 * @date 2021/9/7 17:47
 */
@WebServlet("/user/track")
public class TrackServlet extends HttpServlet {
    private EmpService empService = new EmpServiceImpl();
    private TrackService trackService = new TrackServiceImpl();

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

    public int getE_id(HttpServletRequest req) {
        ServletContext context = req.getServletContext();
        Token token = (Token) context.getAttribute((String) req.getAttribute("token"));
        String username = token.getUsername();
        return empService.getEmpId(username);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //相应json对象
        JsonUtil<String> json = new JsonUtil<>();
        String responseString = "";
        PrintWriter out = resp.getWriter();

        //获取参数
        String c_id = req.getParameter("c_id");
        //String e_id = req.getParameter("e_id");
        String record = req.getParameter("record");
        String intention = req.getParameter("intention");

        Track track = new Track();
        Customer customer = new Customer();
        Emp emp = new Emp();

        customer.setC_id(Integer.parseInt(c_id));
        //获取当前员工的编号
        emp.setE_id(getE_id(req));
        track.setCustomer(customer);
        track.setEmp(emp);
        track.setRecord(record);
        track.setIntention(intention);

        //处理
        int i = trackService.addTrack(track);

        if (i > 0) {
            responseString = json.toString(1000, "OK", "");
        } else {
            responseString = json.toString(1005, "操作失败", "跟踪表添加信息失败");
        }

        out.write(responseString);
        out.close();
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //相应json对象
        JsonUtil<String> json = new JsonUtil<>();
        String responseString = "";
        PrintWriter out = resp.getWriter();

        //获取参数
        String t_id = req.getParameter("t_id");

        int i = trackService.deleteTrack(Integer.parseInt(t_id));

        if (i > 0) {
            responseString = json.toString(1000, "OK", "");
        } else {
            responseString = json.toString(1005, "操作失败", "跟踪表删除信息失败");
        }

        out.write(responseString);
        out.close();
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //相应json对象
        JsonUtil<String> json = new JsonUtil<>();
        String responseString = "";
        PrintWriter out = resp.getWriter();

        //获取参数
        String t_id = req.getParameter("t_id");
        String c_id = req.getParameter("c_id");
        String record = req.getParameter("record");
        String intention = req.getParameter("intention");

        Track track = new Track();
        Customer customer = new Customer();
        Emp emp = new Emp();

        track.setT_id(Integer.parseInt(t_id));
        customer.setC_id(Integer.parseInt(c_id));
        track.setCustomer(customer);
        track.setRecord(record);
        track.setIntention(intention);

        int i = trackService.updateTrack(track);

        if (i > 0) {
            responseString = json.toString(1000, "OK", "修改成功");
        } else {
            responseString = json.toString(1005, "操作失败", "跟踪表修改信息失败");
        }

        out.write(responseString);
        out.close();

    }

    private void findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = "";
        String tokenString = (String) req.getAttribute("token");
        Token token = (Token) req.getServletContext().getAttribute(tokenString);
        username = token.getUsername();
        //相应json对象
        JsonUtil<List<Track>> json = new JsonUtil<>();
        String responseString = "";
        PrintWriter out = resp.getWriter();

        List<Track> trackList = trackService.findAllTrack(username);

        responseString = json.toString(1000, "OK", trackList);
        out.write(responseString);
        out.close();
    }


    private void find(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //相应json对象
        JsonUtil<Track> json = new JsonUtil<>();
        String responseString = "";
        PrintWriter out = resp.getWriter();

        //获取参数
        String t_id = req.getParameter("t_id");

        Track track = trackService.findTrack(Integer.parseInt(t_id));

        responseString = json.toString(1000, "OK", track);
        out.write(responseString);
        out.close();
    }
}
