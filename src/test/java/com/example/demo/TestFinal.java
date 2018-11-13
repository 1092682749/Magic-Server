package com.example.demo;

import com.example.demo.model.ChatMsg;

public class TestFinal {
    public void t(){
        final ChatMsg chatMsg = new ChatMsg();
        new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("run 开始");
                    Thread.sleep(3000);
                    System.out.println("现在开始使用final引用");
                    System.out.println(chatMsg.getClass());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public static void main(String[] args) {
        System.out.println("begin");
        new TestFinal().t();
        System.out.println("t end");
    }
}
