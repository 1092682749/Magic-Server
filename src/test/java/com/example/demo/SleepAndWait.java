package com.example.demo;

public class SleepAndWait {
    public static void main(String[] args) throws InterruptedException {
//        线程一开始执行五秒后执行线程二
        new Thread1().start();
        Thread.sleep(5000);
        new Thread2().start();
    }
}
class Thread1 extends Thread {
    @Override
    public void run() {
        // 获取SleepAndWait类对象锁
        synchronized (SleepAndWait.class) {
            System.out.println("thread1开始执行");
            try {
                // 释放线程锁并进入阻塞状态，需要notify方法进行唤醒，
                // 如果没有收到该方法唤醒即使抢占的对象锁处于未被锁定状态仍然不会醒来
                SleepAndWait.class.wait(); // 让出cpu（即该对象的监听者不监听该线程），同时释放对象锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("thread1完全退出");
    }
}
class Thread2 extends Thread {
    @Override
    public void run() {
        synchronized (SleepAndWait.class) {
            System.out.println("thread2开始执行");
            // 唤醒其他线程
            SleepAndWait.class.notifyAll();// 让出cpu，但不会释放锁
            System.out.println("已通知其他等待对象");
            try {
                Thread.sleep(5000); // 休眠不会让出对象锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread2完全退出");
        }

    }
}