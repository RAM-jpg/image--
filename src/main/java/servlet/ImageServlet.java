package servlet;

import dao.ImageDAO;
import entity.Image;

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
@WebServlet("/show.jsp")
public class ImageServlet extends HttpServlet {

    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            //分页
            String pageStr = request.getParameter("page");
            int page = 1;
            if (null != pageStr && !"".equals(pageStr)) {
                page = Integer.parseInt(pageStr);
            }
            List image = ImageDAO.login();
            int totalImages = image.size();
            int totalPage = totalImages % 6 > 0 ? totalImages / 6 + 1 : totalImages / 6;

            request.setAttribute("curPage", page);
            request.setAttribute("prePage", page > 1 ? page - 1 : 1);
            request.setAttribute("nextPage", totalPage > page ? page + 1 : totalPage);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("image", Image.getImages(page,6, image));

            request.getRequestDispatcher("WEB-INF/show.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
        }

    }
}
