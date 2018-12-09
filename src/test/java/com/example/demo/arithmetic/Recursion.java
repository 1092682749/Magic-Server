package com.example.demo.arithmetic;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Recursion {
    int num = 0;

    public Long doubleIncrease(Long n) {
        System.out.println(n);
        if (n < 5000) {
            doubleIncrease(2 * n);
            System.out.println("增加了" + ++num + "次");
        }
        System.out.println(n);
        return n;
    }
    public void calculateAge(int order, int age) {
        if (order < 8) {
            calculateAge(++order, 2+age);
        } else {
            System.out.println(age);
        }
    }
    public static void main(String[] args) {
        Recursion recursion = new Recursion();
        recursion.calculateAge(1,10);
    }
}
