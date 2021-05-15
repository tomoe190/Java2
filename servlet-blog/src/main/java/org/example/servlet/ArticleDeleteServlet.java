package org.example.servlet;

import org.example.model.Article;
import org.example.model.JSONResponse;
import org.example.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/articleDelete")
public class ArticleDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        JSONResponse json = new JSONResponse();

        try {
            // 1、解析请求数据
            Article a = JSONUtil.deserialize(
                    req.getInputStream(),Article.class
            );
            // 2、业务处理：
            json.setSuccess(true);
        } catch (IOException e) {
            e.printStackTrace();
            json.setCode("ERROR");
            json.setMessage("文章删除出错");
        }
        
        String s = JSONUtil.serialize(json);
        resp.getWriter().println(s);


    }
}
