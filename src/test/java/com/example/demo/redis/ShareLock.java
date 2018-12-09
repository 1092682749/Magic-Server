package com.example.demo.redis;

import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShareLock {
    // 需要竞争的锁key
    public static final String LOCK = "shareLock";

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
//        Jedis jedis = new Jedis("localhost", 6379, 1000000);
        for (int i = 0; i < 10; i++) {
            service.execute(new MyRunnable());
        }
        service.shutdown();
    }
}

class MyRunnable implements Runnable {
    Jedis jedis;
    int count = 0; // 最多重入次数

    MyRunnable() {
        this.jedis = new Jedis("localhost", 6379, 1000000);
    }
    MyRunnable(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public void run() {
        Boolean isSuccess = false; // 是否成功拿到
        while (!isSuccess && count < 100) {
            count++;
            System.out.println("线程" + Thread.currentThread().getName() + "尝试获取锁");
            Long is = jedis.setnx(ShareLock.LOCK, new Date().getTime() + ""); //set if not exist
            if (is == 1L) { // 当前缓存无锁key
                System.out.println("线程" + Thread.currentThread().getName() + "获取成功1");
                isSuccess = true;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                jedis.del(ShareLock.LOCK);
            } else { // 当前缓存存在锁key，就获取时间戳判断是否过期
                String oldTimeStr = jedis.get(ShareLock.LOCK);
                Long oldTime = Long.parseLong(oldTimeStr);
                if ((new Date().getTime() - oldTime) > 5000) { // 如果锁过期
                    String getOldStr = jedis.getSet(ShareLock.LOCK, new Date().getTime() + ""); // 替换旧时间戳并返回旧时间戳
                    if (getOldStr.equals(oldTimeStr)) { // 如果返回的就时间戳和上一次获取一致表示成功获取到了锁
                        System.out.println("线程" + Thread.currentThread().getName() + "获取成功2");
                        isSuccess = true;
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        jedis.del(ShareLock.LOCK);
                    }
                }
                if (!isSuccess) {
                    try {
                        // 三秒后再次尝试
                        Thread.sleep(3000);
                        System.out.println("重入");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

