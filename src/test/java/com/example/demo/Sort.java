package com.example.demo;

import java.util.*;


public class Sort {
    public static void main(String[] args) {
        List<Date> dates = new ArrayList<>();
        dates.add(new Date(1123123));
        dates.add(new Date(234243));
        dates.add(new Date(345345));
        Collections.sort(dates, new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                long res = o1.getTime() - o2.getTime();
                return res > 0 ? 1 : -1;
            }
        });
        System.out.println(dates.get(1));
    }
}
