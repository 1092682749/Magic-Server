package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SuperClazz {
    public static String sid = "1";

    public static void main(String[] args) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 1, 1);
        SimpleDateFormat sm = new SimpleDateFormat("yyyy/MM/DD");
        Date date = sm.parse("2020/2/2");
        Date date1 = sm.parse("2019/10/01");
        System.out.println(date.after(date1));
    }
}
