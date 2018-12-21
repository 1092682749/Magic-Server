package com.example.demo.configration.CustomAnnotationHandler;

import com.example.demo.utils.annotation.TestProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
@Component
public class AnnotationHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    AnnotationHandler annotationHandler;
    @Override
    @SuppressWarnings("Duplicates")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Boolean isLogin = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
//        if (isLogin) {
//            Object o = SecurityContextHolder.getContext().getAuthentication().getDetails();
//            System.out.println(o);
//            return true;
//        }
        String uri = request.getRequestURI();
        String requestStr = request.getRequestURL().toString();
        List<Class<?>> classList = annotationHandler.scan();
        Boolean isOk = true;
        for (Class c : classList) {
            Method[] methods = c.getMethods();
            for (Method m : methods) {
                Annotation requestMapping = m.getAnnotation(RequestMapping.class);
                Annotation testProxy = m.getAnnotation(TestProxy.class);
                if (requestMapping == null || testProxy == null) {
                    continue;
                }
                String[] mappings = ((RequestMapping)requestMapping).value();
                StringBuilder sb = new StringBuilder();
                for (String s : mappings) sb.append(s);
                if ( sb.toString().equals(uri)) {
                    Annotation annotation = m.getAnnotation(TestProxy.class);
                    String permission = ((TestProxy) annotation).value();
                    Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    UserDetails details = (UserDetails) o;
                    Collection<SimpleGrantedAuthority> collection = (Collection<SimpleGrantedAuthority>) details.getAuthorities();
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
                    isOk = collection.contains(authority);
                    System.out.println("结果是" + isOk);
                }
            }
        }
        return isOk;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
