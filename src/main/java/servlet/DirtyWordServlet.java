package servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * @author OK绷38号
 */
public class DirtyWordServlet extends HttpServletRequestWrapper {
    public DirtyWordServlet(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    private static List<String> list = new ArrayList<>();
    static {
        list.add("操");
        list.add("搞基不");
        list.add("蔡徐坤");
        list.add("坤坤我爱你");
        list.add("啊不要啊");
    }

    private HttpServletRequest request;


    @Override
    public String getParameter(String name) {

        String value = request.getParameter(name);
        if(value==null) {
            return null;
        }
        for(String word : list){
            value = value.replaceAll(word, "**");
        }
        return value;
    }
}
