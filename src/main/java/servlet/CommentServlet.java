package servlet;

import dao.CommentDAO;
import dao.ImageDAO;
import entity.Comment;
import utils.FindNameById;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author OK绷38号
 */
@WebServlet("/comment.jsp")
public class CommentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {

            Integer loginUserId;
            if (request.getParameter("imageId") == null) {
                Integer id = (Integer) request.getServletContext().getAttribute("imageId");
                loginUserId = id;
            } else {
                loginUserId = Integer.parseInt(request.getParameter("imageId"));
            }

            request.getSession().setAttribute("imageId", loginUserId);
            String imageName = FindNameById.foundImagesName(loginUserId);
            request.getSession().setAttribute("imageName", imageName);

            String imageUrl = ImageDAO.foundVideoUrl(loginUserId);
            request.setAttribute("src", imageUrl);

            //分页
            String pageStr = request.getParameter("page");
            int page = 1;
            if (null != pageStr && !"".equals(pageStr)) {
                page = Integer.parseInt(pageStr);
            }
            List comments = CommentDAO.login(loginUserId);

            int totalComments = comments.size();
            int totalPage = totalComments % 6 > 0 ? totalComments / 6 + 1 : totalComments / 6;

            request.setAttribute("curPage", page);
            request.setAttribute("prePage", page > 1 ? page - 1 : 1);
            request.setAttribute("nextPage", totalPage > page ? page + 1 : totalPage);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("comments", Comment.getComments(page, 6, comments));

            request.getRequestDispatcher("WEB-INF/comment.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
