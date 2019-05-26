package com.example.demo;

import com.example.Get;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

public class CallOpen {
    public static void main(String[] args) {
        Get g = new Get();
        Get g1 = new Get();
        Get g2 = new Get();
        List<Get> getList = new LinkedList<>();
        Collections.addAll(getList, g, g1, g2);
//        PriorityQueue<Get> getPriorityQueue = new PriorityQueue<>();
        for (int i = 0; i < 5; i++) {
            int finalI = i + 1;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Get get = getList.get(1);

                    try {
                        get.openDevice(finalI *100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }


    }
}
