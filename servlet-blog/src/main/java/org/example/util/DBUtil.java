package org.example.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
//    public static Connection getConnection() throws SQLException {
//        // 每次会新创建池，每次创建新的物理连接，效率较差
//        MysqlDataSource ds = new MysqlDataSource();
//        ds.setURL("jdbc:mysql://localhost:3306/servlet_blog");
//        ds.setCharacterEncoding("UTF-8");
//        ds.setUser("root");
//        ds.setPassword("xc121314");
//        ds.setUseSSL(false);
//        Connection c = ds.getConnection();
//        System.out.println(c);
//        return c;
//    }

    // 类初始化的时候，创建连接池对象，及设置属性(使用同一个连接池复用连接对象)
    // 多线程，调整为双重校验锁的单例模式 TODO
    private static MysqlDataSource ds = new MysqlDataSource();

    static {
        ds.setURL("jdbc:mysql://localhost:3306/servlet_blog");
        ds.setCharacterEncoding("UTF-8");
        ds.setUser("root");
        ds.setPassword("xc121314");
        ds.setUseSSL(false);
    }

    public static Connection getConnection() throws SQLException {
        Connection c = ds.getConnection();
        System.out.println(c);
        return c;
    }

    public static void close(Connection c, PreparedStatement ps, ResultSet rs) throws SQLException {
        if(rs != null)
            rs.close();
        if(ps != null)
            ps.close();
        if(c != null)
            c.close();
    }

    public static void close(Connection c, PreparedStatement ps) throws SQLException {
        close(c,ps,null);
    }
}
