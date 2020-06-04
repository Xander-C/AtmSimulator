package com.xander.atm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

public class SessionInterceptor implements HandlerInterceptor {

    public final static String ADMIN_REG = "/admin[\\s\\S]*";
    public final static String LOGIN_REG = "/login[\\s\\S]*";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        Object object = request.getSession().getAttribute("user");
        if (Pattern.matches(LOGIN_REG, request.getRequestURI())&&object!=null) {
            response.sendRedirect("/index");
            return false;
        }
        if (Pattern.matches(LOGIN_REG, request.getRequestURI())) {
            return true;
        }
        if (null == object) {
            response.sendRedirect("/login");
            return false;
        }else if ((int)request.getSession().getAttribute("user") == 9999) {
            return true;
        }else if (Pattern.matches(ADMIN_REG, request.getRequestURI())){
            response.sendRedirect("/index");
            return false;
        }
        return true;
    }
}
