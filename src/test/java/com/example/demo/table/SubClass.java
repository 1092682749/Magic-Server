package com.example.demo.table;

import com.example.demo.model.User;

import javax.validation.constraints.Size;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class SubClass extends FatherClass {
    public static void main(String[] args) {
        Class<FatherClass> clazz = FatherClass.class;
        List<Annotation> annotationList = Arrays.asList(clazz.getDeclaredAnnotations());
        System.out.println(annotationList.size());
        Stream<Annotation> stream = annotationList.stream();
        List<String> stringList = new ArrayList<>();
        stringList.add("aaa");
        User user = new User();

        stringList.forEach(user::setUsername);
        System.out.println(user.getUsername());

        new SubClass().check("aaaaa", r -> r.length() > 3);
    }
    private Map<String,Predicate<User>> getCheckParamRules() {
        Map<String, Predicate<User>> map = new HashMap<>();
        map.put("平台现价需要为0-无穷大", r -> !r.getUsername().equals(""));
//        map.put("平台的platform不能为空", r -> ParamChecker.isBlank(r.getPlatform()));
        return map;
    }
    void check(String str, Predicate<String> predicate) {
        if (predicate.test(str)) {
            System.out.println(str + "长度符合");
        } else {
            System.out.println(str + "长度不符合");
        }
    }
}
