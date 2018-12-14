package com.example.demo.configration.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Collection;

public class MyRolePermissionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails details = (UserDetails) o;
        Collection<GrantedAuthority> list = (Collection<GrantedAuthority>) details.getAuthorities();
        for (GrantedAuthority authority : list) {
            if (authority.getAuthority().equals("qq")) return true;
        }
        PrintWriter writer = response.getWriter();
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        writer.write("<html>"+"permission miss<a href='/index'>back index</a>"+"</html>");
        writer.flush();
//        response.setHeader("refresh","3;/index");
//        response.encodeRedirectURL("/index");
//        response.setCharacterEncoding("utf-8");
//        response.sendRedirect("/index");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
