package config.interceptor;

import com.legalups.sufatong.common.model.LoginInfo;
import com.legalups.sufatong.common.support.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author : wangyz
 * @Description :
 * @Date :  2017/6/5
 */
@Slf4j
public class CrossFilter implements HandlerInterceptor, InitializingBean {
    @Value("${sessionKey}")
    private String sessionKey;

    private String notLogin;

    public String getNotLogin() {
        return notLogin;
    }

    public void setNotLogin(String notLogin) {
        this.notLogin = notLogin;
    }

    protected void setHeader(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", request.getHeader("Access-Control-Request-Method"));
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        setHeader(httpServletRequest, httpServletResponse);

//
        log.info("请求地址：{}",httpServletRequest.getRequestURL());

        if (httpServletRequest.getRequestURL().indexOf("login") != -1){
            log.info("login:"+httpServletRequest.getSession().getId());
            Cookie cookie = new Cookie(sessionKey,httpServletRequest.getSession().getId());
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
//            String cookie = CookieUtil.getCookieValue(httpServletRequest, "username");
//            httpServletRequest.getSession().setAttribute(cookie,"");
            return  true;
        }

//       String header = CookieUtil.getCookieValue(httpServletRequest, sessionKey);
//        if (StringUtils.isBlank(header)){
//            httpServletResponse.setContentType("application/javascript;charset=UTF-8");
//            httpServletResponse.getWriter().write(context.getNotLoginJsonString());
//            return false;
//        }

        Cookie[] cookies = httpServletRequest.getCookies();
        String sessionId = "";
        for (Cookie cookie : cookies){
            if (cookie.getName().equals(sessionKey)){
                sessionId = cookie.getValue();
            }
        }
        log.info("mvc:"+sessionId);
        LoginInfo user = (LoginInfo) httpServletRequest.getSession().getAttribute(sessionId);
        if (user == null) {
            httpServletResponse.setContentType("application/javascript;charset=UTF-8");
            httpServletResponse.getWriter().write("no login");
            return false;
        }
        Cookie cookie = new Cookie(sessionKey,httpServletRequest.getSession().getId());
//            cookie.set
        httpServletResponse.addCookie(cookie);
        AuthUtil.setUserInfo(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    protected void writeNotLoginResponse(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String callback = request.getParameter("callback");
        if (!StringUtils.isBlank(callback)) {
            response.setContentType("application/javascript;charset=UTF-8");
            response.getWriter().write(callback + "(未登录);");
            response.getWriter().flush();
            return;
        }

//        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))||context.isForApp()||
//                getCurrentUri(request).endsWith(".json")) {
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().write(notLoginJsonString);
//            response.getWriter().flush();
//            return;
//        }

//        response.sendRedirect(buildLoginUrl(request));
    }
}