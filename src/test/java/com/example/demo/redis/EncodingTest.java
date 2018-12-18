package com.example.demo.redis;


import com.example.demo.DemoApplicationTests;
import com.example.demo.model.User;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

import javax.xml.ws.Action;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EncodingTest extends DemoApplicationTests {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    Jedis jedis;

    //    @Test
//    public void test(){
//        User user = new User();
//        user.setUsername("momo");
//        redisTemplate.opsForValue().set("user",user);
//        User ref = (User) redisTemplate.opsForValue().get("user");
//        System.out.println(ref.getUsername());
//    }
    @Test
    public void test() {
        Jedis jedis = new Jedis("dyzhello.club", 9999, 100000);
        jedis.auth("dyz");
//        new java.lang.Thread(new Thread2(jedis)).start();
        jedis.set("testConnection", "succeed");
        System.out.println("Connection to server sucessfully");
        //查看服务是否运行
//        System.out.println("Server is running: " + jedis.ping());
    }

//    @Test
//    public void test3() {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (int i = 0; i < 10; i++) {
////            executorService.execute(new Thread2(jedis));
//            new Thread(new Thread2(jedis)).start();
//        }
//    }
//
//    class Thread2 implements Runnable {
//        Jedis jedis;
//
//        Thread2(Jedis jedis) {
//            this.jedis = new Jedis("localhost", 6379, 1000000);
//        }
//
//        @Override
//        public void run() {
//            Boolean isSuccess = false;
//            while (!isSuccess) {
//                System.out.println("线程" + Thread.currentThread().getName() + "尝试获取锁");
//                Long is = jedis.setnx("shareLock", new Date().getTime() + "");
//                if (is == 1L) {
//                    System.out.println("线程" + Thread.currentThread().getName() + "获取成功1");
//                    isSuccess = true;
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    jedis.del("shareLock");
//                } else {
//                    System.out.println("重入");
//                    String oldTimeStr = jedis.get("shareLock");
//                    Long oldTime = Long.parseLong(oldTimeStr);
//                    if ((new Date().getTime() - oldTime) > 5000) {
//                        String getOldStr = jedis.getSet("shareLock", new Date().getTime() + "");
//                        if (getOldStr.equals(oldTimeStr)) {
//                            System.out.println("线程" + Thread.currentThread().getName() + "获取成功2");
//                            isSuccess = true;
//                            try {
//                                Thread.sleep(3000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            jedis.del("shareLock");
//                        }
//                    } else {
//                        try {
//                            Thread.sleep(3000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//    }

    @Test
    public void test1() {
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            new Thread1(connection).start();
//            service.execute(new Thread1(connection));
        }
    }

    class Thread1 extends Thread {
        RedisConnection connection;
        int count = 0;

        Thread1(RedisConnection connection) {
            this.connection = connection;
        }

        @Override
        public void run() {
            Boolean isSuccess = false;
            isSuccess = connection.setNX("lock".getBytes(), new Date().toString().getBytes());
            do {
                count++;
                System.out.println("第" + count + "次尝试获取锁");
                if (isSuccess) {
                    try {
                        System.out.println("获得共享锁");
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        byte[] res = connection.get("lock".getBytes());
                        System.out.println(res);
                        System.out.println("未获得共享锁五秒后重试");
                        Thread.sleep(5000);
                        connection.getSet("lock".getBytes(), new Date().toString().getBytes());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } while (!isSuccess && count < 10);
        }
    }
}

