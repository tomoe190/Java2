package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        // 解析我们的请求数据： header method URL
        // 通过键获取值，如果该键没有值则返回null
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        PrintWriter pw = resp.getWriter();

        // 数据库根据客户端用户输入的账号密码，查询有没有该数据
        // 简单模拟
        if("abc".equals(username) && "123".equals(password)) {
            pw.println("<h1>欢迎你：" + username + "</h1>");
        } else {
            pw.println("<h1>用户名密码错误</h1>");
        }

    }
}
