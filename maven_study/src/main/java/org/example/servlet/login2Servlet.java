package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login2")
public class login2Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Servlet 返回json 代码，统一的代码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        /**
         * 1、解析请求数据
         * （1)request.getParameter("键") 获取值
         *     queryString body 格式和 queryString 一样
         * （2）请求头 ContentType 为 application/json
         *     此时请求体为json 字符串
         *     request.getInputString()获取输入流
         *     通过输入流来获取body的数据
          */
        // 目前是json字符串作为请求体内容,需要反序列化Java对象
        ObjectMapper mapper = new ObjectMapper();
        // 通过  获取输入流（包含body数据）
        InputStream is = req.getInputStream();
        // 请求数据反序列化为Java对象，其实就是用户输入的数据
        User input = mapper.readValue(is,User.class);

        PrintWriter pw = resp.getWriter();
        Map<String,Object> map = new HashMap<>();

        // 2、根据请求数据执行业务
        if ("abc".equals(input.getUsername()) &&
            "123".equals(input.getPassword())) {
            // 业务操作成功，有些接口需要返回业务数据
            map.put("ok",true);
        } else {
            // 业务操作失败，还需要返回错误码（开发人员），错误信息（用户看）
            map.put("ok",false);
            map.put("code","LOG001");
            map.put("msg","用户名或密码错误");
        }

        // 3、返回响应的业务数据
        pw.println(mapper.writeValueAsString(map));

    }
}
