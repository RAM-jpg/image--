package servlet;

import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author OK绷38号
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        request.getSession().setAttribute("username", username);

        if(username==null||password==null){
            request.setAttribute("msg","您的输入有误，请重新输入");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }else {
            try {
                boolean flag = UserDAO.login(username,password);
                if(flag){
                    //cookie标记
                    Cookie cookie = new Cookie("username",request.getParameter("username"));
                    cookie.setPath("/");
                    cookie.setMaxAge(60*60*24);
                    response.addCookie(cookie);

                    response.sendRedirect("show.jsp");
                }else{
                    request.setAttribute("msg","您的输入有误，请重新输入");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            } catch (SQLException | ServletException | IOException e) {
                e.printStackTrace();
            }
        }

    }
}
