package com.example.demo;

public class DXC {
    static  int count = 1;
    public static void main(String[] args) throws Exception {
        Thread thread= new Thread(() -> {
            int v = 0;
            while (count == 1) {
                System.out.println(count);
            }
            System.out.println("result");
        });
        thread.start();
        Thread.sleep(100);
        count++;
        System.out.println(count);
        thread.join();
    }
}
