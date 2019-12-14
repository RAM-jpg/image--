package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author OK绷38号
 */
public class FindNameById {

    private static Connection con = ConnectionUtil.getConnection();;
    private static PreparedStatement pstmt ;
    private static ResultSet resultSet = null;

    public static String foundUserName(int id) throws SQLException {

        String sql = "select id,userName from user where id = ? ";
        pstmt = (PreparedStatement) con.prepareStatement(sql);
        pstmt.setInt(1, id);
        resultSet = pstmt.executeQuery();
        if (resultSet.next()) {
            int temp = resultSet.getInt("id");
            if (temp==id) {
                return resultSet.getString("userName");
            }
        } else {
            System.out.println("输入错误！");
            return null;
        }
        return null;
    }
    public static String foundImagesName(int id) throws SQLException {

        String sql = "select image_id,image_name from image where image_id = ? ";
        pstmt = (PreparedStatement) con.prepareStatement(sql);
        System.out.println(id);
        pstmt.setInt(1, id);
        resultSet = pstmt.executeQuery();
        if (resultSet.next()) {
            int temp = resultSet.getInt("image_id");
            if (temp==id) {
                return resultSet.getString("image_name");
            }
        } else {
            System.out.println("输入错误！");
            return null;
        }
        return null;
    }
}
