package utils;

import java.util.UUID;

/**
 * @author OK绷38号
 */
public class UploadUtil {
    public static String getUUIDFileName(String fileName){
        // 将文件名的前面部分进行截取：xx.jpg   --->   .jpg
        int idx = fileName.lastIndexOf(".");
        String extention = fileName.substring(idx);
        return UUID.randomUUID().toString().replace("-", "")+extention;
    }
}
