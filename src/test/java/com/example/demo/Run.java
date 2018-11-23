package com.example.demo;

public class Run {
    static String os;
    public void m() {
        class Mc {
            Mc() {
                System.out.println("MC!");
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
    }
    public class Inner {
        Inner() {
            os = "asd";
        }
    }
}
