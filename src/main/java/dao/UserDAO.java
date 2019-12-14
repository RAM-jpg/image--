package dao;

import entity.User;
import utils.ConnectionUtil;

import java.sql.*;

/**
 * @author OK绷38号
 */
public class UserDAO {

    private static Connection con = ConnectionUtil.getConnection();
    private static PreparedStatement pstmt;
    private static ResultSet resultSet = null;

    //用户登录
    public static boolean login(String username, String password) throws SQLException {
        con = ConnectionUtil.getConnection();
        String sql = "select *from user where userName = ? and password = ?";
        pstmt = (PreparedStatement) con.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        resultSet = pstmt.executeQuery();
        if (resultSet.next()) {
            User user = new User();
            user.setUsername(resultSet.getString("userName"));
            user.setPassword(resultSet.getString("password"));
            System.out.println("登录成功！");
            return true;
        } else {
            System.out.println("输入错误！");
            ConnectionUtil.release(resultSet, null, con, pstmt);
            return false;
        }
    }

    //用户注册
    public static boolean register(String username, String password) throws SQLException {

        try {
            String sql = "insert into user (userName,password)values(?,?) ";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            ConnectionUtil.release(resultSet, null, con, pstmt);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionUtil.release(resultSet, null, con, pstmt);
        return false;
    }
}
