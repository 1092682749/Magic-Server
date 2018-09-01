package com.example.demo;

import java.util.concurrent.atomic.AtomicInteger;

public class ShareClass {

    /***
     * 这里写成public是为了不写get方法 ，
     * 在实际生产中必须写成private以保证不会被外部方法更改
     */

    public static AtomicInteger id = new AtomicInteger(0);
    public static volatile Integer iid = 0;
    Integer ii;
    ShareClass(){
//        synchronized (id){
            id.addAndGet(1);
            iid++;
//        }
    }
}
