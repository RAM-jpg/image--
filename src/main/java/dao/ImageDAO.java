package dao;

import entity.Image;
import utils.ConnectionUtil;
import utils.FindNameById;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author OK绷38号
 */
public class ImageDAO {

    private static Connection con = ConnectionUtil.getConnection();;
    private static PreparedStatement pstmt ;
    private static ResultSet resultSet = null;
    private  static boolean flag=true;

    public static boolean upload(String userName, String imageName, String imageUrl) throws SQLException {

        int Id = foundId(userName);
        System.out.println("用户id:" + Id);
        String sql = "insert into image (user_id,image_name,image_url)values(?,?,?) ";

        try {

            pstmt = con.prepareStatement(sql);
            if (imageName == null || imageName.trim().isEmpty() || imageUrl == null || imageUrl.trim().isEmpty()) {
                System.out.println("图片存入数据失败");
                return flag = false;
            } else {
                System.out.println("图片存入数据成功");
                pstmt.setInt(1, Id);
                pstmt.setString(2, imageName);
                pstmt.setString(3, imageUrl);
                pstmt.executeUpdate();

                return flag = true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static int foundId(String userName) throws SQLException {
        System.out.println("传入的用户名" + userName);
        String sql = "select id,userName from user where userName = ? ";
        pstmt = (PreparedStatement) con.prepareStatement(sql);
        pstmt.setString(1, userName);
        resultSet = pstmt.executeQuery();
        if (resultSet.next()) {

            if (resultSet.getString("userName").equals(userName)) {
                return resultSet.getInt("id");
            }

        } else {
            System.out.println("输入错误！");
            return -1;
        }
        return -1;
    }

    public static List<Image> login() throws SQLException {

        String sql = "select user_id,image_id,image_name from image ";
        pstmt = con.prepareStatement(sql);
        resultSet = pstmt.executeQuery(sql);
        List<Image> images = new ArrayList<>();
        while (resultSet.next()) {
            Image image = new Image();
            image.setImageId(resultSet.getInt("image_id"));
            image.setImageName(resultSet.getString("image_name"));
            String userName = FindNameById.foundUserName(resultSet.getInt("user_id"));
            image.setUserName(userName);
            images.add(image);
        }
        return images;
    }

    public static String foundVideoUrl(Integer imageId) throws SQLException {

        System.out.println("传入图片id"+imageId);
        String sql = "select image_id,image_url from image where image_id = ? ";
        pstmt = (PreparedStatement) con.prepareStatement(sql);
        pstmt.setInt(1, imageId);
        resultSet = pstmt.executeQuery();
        if (resultSet.next()) {

            if (resultSet.getInt("image_id")==imageId) {
                return resultSet.getString("image_url");
            }

        } else {
            System.out.println("输入错误！");

        }
        return null;
    }
}
