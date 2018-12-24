package com.example.demo.configration;

import com.example.demo.configration.CustomAnnotationHandler.AnnotationHandlerInterceptor;
import com.example.demo.configration.security.MyRolePermissionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    AnnotationHandlerInterceptor annotationHandlerInterceptor;
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
//        registry.addViewController("/").setViewName("index");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/regist").setViewName("regist");
    }
    //解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/uploads/**").addResourceLocations("file:../uploads/");
        registry.addResourceHandler("/static/uploads/**").addResourceLocations("/static/uploads/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyRolePermissionInterceptor()).addPathPatterns("/user/**");
        // 权限注解拦截，暂不需要
//        registry.addInterceptor(annotationHandlerInterceptor).addPathPatterns("/ok/**");
    }
}
