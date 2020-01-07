package com.example.demo.fuli;

public class Son extends Father {
    @Override
    public void ml() {
        System.out.println("s ml");
    }
    public static void main(String[] args) {
        Father s = new Son();
        s.ml();
    }
}
