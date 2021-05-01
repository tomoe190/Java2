package org.example.dao;

import org.example.model.User;
import org.example.util.DBUtil;

import java.sql.*;
import java.util.Date;

public class UserDAO {

    public static User query(User user) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = DBUtil.getConnection();
            String sql = "select id,nickname,sex,birthday," +
                    "head from user where username=? and password=?";
            ps = c.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            rs = ps.executeQuery();
            User query = null;
            while (rs.next()) {
                query = new User();
                query.setId(rs.getInt("id"));
                query.setUsername(user.getUsername());
                query.setPassword(user.getPassword());
                query.setNickname(rs.getString("nickname"));
                query.setSex(rs.getBoolean("sex"));
                // 格林威治时间，得到的是一个Timestamp 类型的数据
                // 由于从结果集获取数据可能为空，所以要进行一个非空的判断
                Timestamp t = rs.getTimestamp("birthday");
                if (t != null)
                    query.setBirthday(new Date(t.getTime()));// 转换
                query.setHead(rs.getString("head"));
            }
            return query;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(c,ps,rs);
        }
    }
}
