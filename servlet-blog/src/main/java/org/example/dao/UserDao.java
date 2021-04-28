package org.example.dao;

import org.example.model.User;
import org.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

public class UserDao {

    public static User query(User user) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = DBUtil.getConnection();
            String sql = "select id,nickname,sex,birthday,head" +
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
                Timestamp t = rs.getTimestamp("birthday");
                if (t != null)
                    query.setBirthday(new Date(t.getTime()));
                query.setHead(rs.getString("head"));
            }
            return query;
        } catch(Exception e) {
            e.printStackTrace();

        } finally {
            DBUtil.close(c,ps,rs);
        }
    }
}
