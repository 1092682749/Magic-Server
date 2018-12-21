package com.example.demo.configration.CustomAnnotationHandler;

import com.example.demo.utils.annotation.TestProxy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class CGLIBCondition implements MethodInterceptor {
    public Object getProxy(Class cla)
    {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cla);
        enhancer.setCallback(this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Annotation a = method.getAnnotation(TestProxy.class);
        if (a == null) {
            System.out.println(method.getName() + "没有注解TestProxy");
        }
        String val = ((TestProxy) a).value();
        if (val.equals("role")) {
            System.out.println("有权限");
        } else {
            System.out.println("没有权限抛出异常以中断程序");
        }
        Object obj = methodProxy.invokeSuper(o, objects);
        return obj;
    }
}
