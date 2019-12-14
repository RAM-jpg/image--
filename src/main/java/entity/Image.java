package entity;

import java.util.List;

/**
 * @author OK绷38号
 */
public class Image {
    private long imageId;
    private String imageName;
    private String userId;
    private String userName;

    public Image() {
    }

    public Image(String imageName, String userName) {
        this.imageName = imageName;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static List<Image> getImages(int page, int size, List<Image> images){
        int start = (page - 1)*size;
        int end  = Math.min(images.size(), page * size);
        return images.subList(start,end);
    }
}
