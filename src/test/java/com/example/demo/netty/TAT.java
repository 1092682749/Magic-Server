package com.example.demo.netty;

public class TAT {
    public static void main(String[] args){
        new Thread(){
            @Override
            public void run() {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                            System.out.println("子线程的子线程执行完了");

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                System.out.println("子线程执行完了");
            }
        }.start();
        System.out.println("主线程执行完了，等待所有线程结束。。。。。");
    }
}
