package com.legalups.sufatong.common.support;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @Author : wangyz
 * @Description :
 * @Date :  2017/6/4
 */
public abstract class CookieUtil {


    public static String getCookieValue(final HttpServletRequest request, String cookieName) {
        final Cookie cookie = getCookie(request, cookieName);
        if (cookie == null) {
            return null;
        }
        try {
            return URLDecoder.decode(cookie.getValue(), "UTF-8");
        } catch (Exception ex) {
            return cookie.getValue();
        }
    }

    public static void removeCookie(HttpServletResponse response, String cookieName, String cookieDomain) {
        addCookie(response, cookieName, "", cookieDomain, "/", false, 0);
    }

    public static void removeCookie(HttpServletResponse response, String cookieName,
                                    String cookieDomain, String cookiePath, boolean isCookieSecure) {
        addCookie(response, cookieName, "", cookieDomain, cookiePath, isCookieSecure, 0);
    }

    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (int i = cookies.length - 1; i >= 0; i--) {
                if (name.equals(cookies[i].getName())) {
                    return cookies[i];
                }
            }
        }
        return null;
    }

    public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue,
                                 String cookieDomain) {
        addCookie(response, cookieName, cookieValue, cookieDomain, "/", false, Integer.MAX_VALUE);
    }

    public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue,
                                 String cookieDomain, String cookiePath, boolean isCookieSecure, Integer maxAge) {
        String cookieValueEncoded = cookieValue;
        try {
            cookieValueEncoded = URLEncoder.encode(cookieValue, "UTF-8");
        } catch (Exception ex) {
            //do nothing
        }
        Cookie cookie = new Cookie(cookieName, cookieValueEncoded);
        if (cookieDomain != null) {
            cookie.setDomain(cookieDomain);
        }
        cookie.setPath(cookiePath);
        if (maxAge != null) {
            cookie.setMaxAge(maxAge);
        }
        if (isCookieSecure) {
            cookie.setSecure(true);
        }
        response.addCookie(cookie);
    }


}
