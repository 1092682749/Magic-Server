package com.example.demo;

import java.util.Date;

public class Run {
    static String os;
    public void m() {
        class Mc extends Date {
            Mc() {
                System.out.println("MC!" + super.getClass());
            }
        }
        new Mc();
    }
    public static void main(String[] args){
//        PrintScreen4DJNativeSwingUtils.printUrlScreen2jpg("/static","http://localhost:8080/views/front/ImageTest.html",100,200);
        String s = "{asdadada}{adadad}";
        String[] ss = s.split("}");
        System.out.println(ss[1]);
        new Run().m();
        System.out.println(zi());
    }
    public class Inner {
        Inner() {
            os = "asd";
        }
    }
    static public int zi() {
        int x = 1;
        try {
            return x;
        }finally {
            ++x;
        }
    }
}
