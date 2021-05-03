package org.example.dao;

import org.example.model.Article;
import org.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {

    public static List<Article> query(int userId) throws SQLException {
        // jdbc 查询用户关联的文章列表
        // 1、创建连接Connection对象
        Connection c = DBUtil.getConnection();

        // 2、根据连接，创建操作命令 statement
        String sql = "select id,title from article where user_id=?";
        PreparedStatement ps = c.prepareStatement(sql);//预编译
        // 替换占位符的值，第一个参数表示占位符的索引（1开始），第二个参数是要替换的值
        ps.setInt(1,userId);

        // 3、根据操作命令执行SQL
        ResultSet rs = ps.executeQuery();

        List<Article> query = new ArrayList<>();

        // 4、如果是查询操作，处理结果集
        while (rs.next()) { // 移动到下一行，返回 true 就是有数据
            int id = rs.getInt("id");  // 获取id字段的值
            String title = rs.getString("title");
            // 每一行数据，对应一个对象
            Article a = new Article();
            a.setId(id);
            a.setTitle(title);
            query.add(a);
        }

        // 5、释放资源 TODO 存在问题：之前代码抛异常就无法执行close代码
        DBUtil.close(c, ps, rs);

        return query;
    }




    public static void main(String[] args) throws SQLException {
//        List<Article> res = query(1);
//        System.out.println(res);
        query(1);
    }

    public static int insert(Article a,Integer userId) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            // 1、获取数据库连接
            c = DBUtil.getConnection();

            // 2、根据Connection+sql创建 操作命令对象
            String sql = "insert into article(title,content,user_id) values(?,?,?)";
            ps = c.prepareStatement(sql);

            // 3、先替换占位符的值，再执行sql
            ps.setString(1,a.getTitle());
            ps.setString(2,a.getContent());
            ps.setInt(3,userId);
            // 插入、修改、删除都是调用executeUpdate的方法
            // 返回值都是int
            return ps.executeUpdate();
            

//        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            DBUtil.close(c,ps);
        }
        
    }
}
