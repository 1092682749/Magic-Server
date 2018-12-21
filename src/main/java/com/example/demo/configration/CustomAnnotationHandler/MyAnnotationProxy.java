package com.example.demo.configration.CustomAnnotationHandler;

import com.example.demo.utils.annotation.TestProxy;
import sun.jvm.hotspot.utilities.soql.InvocableCallable;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyAnnotationProxy implements InvocationHandler {
    private Object target = null;
    public Object bind(Object target)
    {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
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
        Object obj = method.invoke(target, args);
        return obj;
    }
}
