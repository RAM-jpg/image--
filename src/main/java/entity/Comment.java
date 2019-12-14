package entity;

import java.util.List;

/**
 * @author OK绷38号
 */
public class Comment {

    private long commentId;
    private String comment;
    private String userId;
    private String imageName;
    private String userName;


    public Comment() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public static List<Comment> getComments(int page, int size, List<Comment> comments){

        int start = (page - 1)*size;
        int end  = Math.min(comments.size(), page * size);
        return comments.subList(start,end);
    }
}
