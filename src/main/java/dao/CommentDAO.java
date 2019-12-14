package dao;

import entity.Comment;
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
public class CommentDAO {

    public static boolean commentAdd (String username, Integer imageId, String comment) throws SQLException {
        int ID = ImageDAO.foundId(username);
        System.out.println();
        Connection con;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        boolean flag=true;

        con = ConnectionUtil.getConnection();
        String sql = "insert into comment (user_id,image_id,comment)values(?,?,?) ";

        try {

            pstmt = con.prepareStatement(sql);
            if(imageId==null||comment==null||comment.trim().isEmpty()){
                System.out.println("评论存入失败");
                return flag = false;
            }else {
                System.out.println("评论存入成功");
                pstmt.setInt(1, ID);
                pstmt.setInt(2, imageId);
                pstmt.setString(3, comment);
                pstmt.executeUpdate();
                ConnectionUtil.release(resultSet, null, con, pstmt);
                return flag = true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionUtil.release(resultSet, null, con, pstmt);
        return flag=false;
    }

    public static List<Comment> login(Integer imageId) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        boolean flag = true;
        Connection con = ConnectionUtil.getConnection();
        String sql = "select user_id,image_id,comment from comment where image_id = ?";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, imageId);
        resultSet = pstmt.executeQuery();
        List<Comment> comments= new ArrayList<>();
        while (resultSet.next()) {
            Comment comment = new Comment();
            comment.setComment(resultSet.getString("comment"));
            String userName = FindNameById.foundUserName(resultSet.getInt("user_id"));
            comment.setUserName(userName);
            comments.add(comment);
        }
        ConnectionUtil.release(resultSet, null, con, pstmt);
        return comments;
    }
}
