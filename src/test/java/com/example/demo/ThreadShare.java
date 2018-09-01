package com.example.demo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ThreadShare implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 20000; i++){
            Random random = new Random(20);
           for (int j = 0; j < Math.abs(random.nextInt()); j++){

           }
            ShareClass shareClass = new ShareClass();
            System.out.println("iid is "+shareClass.iid);
        }
    }

    public static void main(String[] args){
        Thread t1 = new Thread(new ThreadShare());
        Thread t2 = new Thread(new ThreadShare());
        t1.start();
        t2.start();
    }
}
