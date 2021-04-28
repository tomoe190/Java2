package org.example.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection() throws SQLException {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL("jdbc:mysql://localhost:3306/servlet_blog");
        ds.setUser("root");
        ds.setPassword("xc121314");
        ds.setUseSSL(false);
        Connection c = ds.getConnection();
        System.out.println(c);
        return c;
    }

    public static void close(Connection c, PreparedStatement ps, ResultSet rs) throws SQLException {
        rs.close();
        ps.close();
        c.close();
    }
}
