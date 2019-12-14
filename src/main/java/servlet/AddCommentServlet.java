package servlet;

import dao.CommentDAO;
import utils.CookieUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author OK绷38号
 */
public class AddCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        Cookie[] cookies = request.getCookies();
        Cookie cookie = CookieUtil.findCookie(cookies, "username");

        assert cookie != null;
        String username = cookie.getValue();

        Integer  imageId = Integer.parseInt(request.getParameter("imageId"));
        try {
            if(CommentDAO.commentAdd(username, imageId, request.getParameter("comment1"))){
                request.getServletContext().setAttribute("imageId",imageId);

                response.sendRedirect("CommentServlet");

            }else{
                System.out.println("出错了！");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
