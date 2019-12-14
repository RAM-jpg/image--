package filter;

import servlet.DirtyWordServlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author OK绷38号
 */
public class WordsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request;
        HttpServletResponse response;
        try{
            request = (HttpServletRequest)req;
            response = (HttpServletResponse)resp;
        }catch(Exception e){
            throw new RuntimeException("non-http request");
        }

        DirtyWordServlet dirtyWordServlet = new DirtyWordServlet(request);
        filterChain.doFilter(dirtyWordServlet, response);

    }


    @Override
    public void destroy() {

    }
}
