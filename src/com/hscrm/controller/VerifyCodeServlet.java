package com.hscrm.controller;

import com.hscrm.util.VerifyCode;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Peter Cheung
 * @user PerCheung
 * @date 2021/9/2 20:35
 */
@WebServlet("/verifyCode")
public class VerifyCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedImage img = new BufferedImage(180, 50, BufferedImage.TYPE_INT_RGB);
        String chars = VerifyCode.drawRandomImg(180, 50, img);

        //将验证码保存在会话对象中
        HttpSession session = req.getSession();
        session.setAttribute("verifyCode", chars);

        resp.setContentType("image/png");
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write(img, "png", out);
        out.close();
    }
}
