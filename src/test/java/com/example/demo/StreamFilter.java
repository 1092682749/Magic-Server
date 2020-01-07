package com.example.demo;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamFilter {
    public static void main(String[] args) {
        List<String> strings = new LinkedList<>();
        strings.add("asd");
        strings.add("zxc");
        Stream<String> ss = strings.stream().filter(item -> {
            if (item.equals("asd"))
            return true;
            return false;
        });
        System.out.println(strings);
        List<String> sss = ss.collect(Collectors.toList());
        System.out.println(sss);
    }
}
