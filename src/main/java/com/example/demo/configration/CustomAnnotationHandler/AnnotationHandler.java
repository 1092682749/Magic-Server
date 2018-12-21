package com.example.demo.configration.CustomAnnotationHandler;

import com.example.demo.utils.annotation.TestProxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "my-annotation")
public class AnnotationHandler {
    public String basePacket;
    public String[] annotations;
    @Autowired
    private DefaultListableBeanFactory defaultListableBeanFactory;


    public String getBasePacket() {
        return basePacket;
    }

    public void setBasePacket(String basePacket) {
        this.basePacket = basePacket;
    }

    public String[] getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String[] annotations) {
        this.annotations = annotations;
    }

    public List<Class<?>> scan() throws ClassNotFoundException {
        String p = AnnotationHandler.class.getClassLoader().getResource("").getPath() + basePacket.replace(".", "/");
        File path = new File(p);
        File[] clazzFileList = path.listFiles();
        List<Class<?>> classList = new LinkedList<Class<?>>();
        for (File c : clazzFileList) {
            System.out.println(basePacket + "." + c.getName());
            String className = basePacket + "." + (c.getName().replace(".class", ""));
            Class<?> clazz = Class.forName(className);
            classList.add(clazz);
        }
        return classList;
    }

//    @Override
//    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        ServletContext sc = servletContextEvent.getServletContext();
//        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
//        try {
//            List<Class<?>> classList = scan();
//            for (Class c : classList) {
//                proxy(c, ctx);
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//    }
//
////    @Override
//    public void contextDestroyed(ServletContextEvent servletContextEvent) {
//
//    }

//    public void proxy(Class<?> clazz, ApplicationContext ctx) throws IllegalAccessException, InstantiationException, InvocationTargetException {
//        Method[] methods = clazz.getMethods();
//        for (Method m : methods) {
//            if (m.getAnnotation(TestProxy.class) != null) {
//                Object o = ctx.getBean(clazz);
//                String orderBeanName = ctx.getBeanNamesForType(clazz)[0];
//                defaultListableBeanFactory.removeBeanDefinition(orderBeanName);
//                CGLIBCondition proxy = new CGLIBCondition();
//                Object o1 = proxy.getProxy(clazz);
////                defaultListableBeanFactory.registerBeanDefinition(orderBeanName, o1);
//            }
//        }
//    }
//
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        if (StringUtils.endsWithIgnoreCase(beanName, targetBeanName)) {
//            boolean containsBean = defaultListableBeanFactory.containsBean(targetBeanName);
//            if (containsBean) {
//                //移除bean的定义和实例
//                defaultListableBeanFactory.removeBeanDefinition(targetBeanName);
//            }
//            //注册新的bean定义和实例
//            defaultListableBeanFactory.registerBeanDefinition(targetBeanName, BeanDefinitionBuilder.genericBeanDefinition(Test55.class).getBeanDefinition());
//            bean = null;
//
//        }
//        return bean;
//    }
}
