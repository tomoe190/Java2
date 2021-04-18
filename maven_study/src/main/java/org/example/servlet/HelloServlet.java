package org.example.servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet 作用
 * 解析http请求
 *
 * 步骤：
 * 1、使用 @WebServlet(/服务路径)
 * 2、继承 HttpServlet
 * 3、重写doXXX方法
 *
 * 重写的方法，都包含两个对象：
 * 一般是通过 request 获取http请求的部分信息
 *     虽然有response对象，但没有给客户端返回http响应数据
 *     自己的程序设置response 对象的内容
 *     tomcat：通过程序方法返回，通过response 对象组装为http响应
 *
 * 注意事项：
 * 1、服务路径必须是 / 开头
 * 2、多个 Servlet ，服务路径不能重复
 */
@WebServlet ("/hello")   // 服务路径
public class HelloServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 所有Servlet 重写的方法都有的比较固定的处理
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        // 模拟响应一个动态的网页，可以从其他地方获取，数据库等
        String name = "李四";
        // 通过 resp 对象获取输出流，输出字符串（body内容）
        PrintWriter out = resp.getWriter();
        out.println("<h1>welcome！" + name + "</h1>");
    }
    

}
