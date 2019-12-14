package utils;

import javax.servlet.http.Cookie;

/**
 * @author OK绷38号
 */
public class CookieUtil {

    public static Cookie findCookie(Cookie[] cookies, String name) {

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }
}
