package servlet;

import dao.ImageDAO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import utils.CookieUtil;
import utils.UploadUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.List;

/**
 * @author OK绷38号
 */
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        //上传
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> list = upload.parseRequest(request);

            Cookie[] cookies = request.getCookies();
            Cookie cookie = CookieUtil.findCookie(cookies, "username");
            assert cookie != null;
            String username = cookie.getValue();

            String url = null;
            for (FileItem fileItem : list) {

                String fileName = fileItem.getName();
                if (fileItem != null && !"".equals(fileName)) {

                    String uuidFileName = UploadUtil.getUUIDFileName(fileName);
                    InputStream is = fileItem.getInputStream();
                    String path = request.getSession().getServletContext().getRealPath("\\");
                    url = path + "\\upload\\" + uuidFileName;
                    OutputStream os = new FileOutputStream(url);
                    int len = 0;
                    byte[] b = new byte[1024];
                    while ((len = is.read(b)) != -1) {
                        os.write(b, 0, len);
                    }

                    if (!ImageDAO.upload(username, fileName, "\\upload\\" + uuidFileName)) {
                        request.setAttribute("msg", "上传失败，请重新上传");
                    }
                    response.sendRedirect("ImageServlet");
                    is.close();
                    os.close();
                }else{
                    response.sendRedirect("ImageServlet");
                }
            }
        } catch (SQLException | FileUploadException | IOException e) {
            e.printStackTrace();
        }
    }
}
