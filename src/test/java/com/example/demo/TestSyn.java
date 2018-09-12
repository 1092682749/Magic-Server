package com.example.demo;

import com.example.demo.model.User;

import java.util.LinkedList;
import java.util.List;

class RobRedPacket{
    List<User> users = null;
    List<User> users2 = null;

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void get(Integer index,String name) throws InterruptedException {
        synchronized (users.get(index)){
            int num = index+1;
            if (name.equals("T1")) {
                Thread.sleep(5000);
            }
            System.out.println("抢了一个"+num+"红包");
        }
    }
}
class T1 implements Runnable {
    RobRedPacket robRedPacket;
    Integer index;
    T1(RobRedPacket redPacket,Integer index){
        this.robRedPacket = redPacket;
        this.index = index;
    }
    @Override
    public void run() {

        try {
            System.out.println("T1开始");
            robRedPacket.get(index,"T1");
            System.out.println("T1结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class T2 implements Runnable{
    RobRedPacket robRedPacket;
    Integer index;

    T2(RobRedPacket redPacket,Integer index){
        this.robRedPacket = redPacket;
        this.index = index;
    }
    @Override
    public void run() {
        System.out.println("T2开始");
        try {
            robRedPacket.get(index,"T2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("T2结束");
    }
}
public class TestSyn {
    public static void main(String[] args){
        //创建两个发红包的用户
        User u1 = new User();
        u1.setId(1);
        u1.setName("111");
        User u2 = new User();
        u2.setId(2);
        u2.setName("222");
        User u3 = new User();
        u1.setId(1);
        u1.setName("111");
        User u4 = new User();
        u2.setId(2);
        u2.setName("222");
        //把用户列表添加到抢红包的list
        RobRedPacket redPacket = new RobRedPacket();
        LinkedList<User> users = new LinkedList<>();
        LinkedList<User> users1 = new LinkedList<>();
        users1.add(u3);
        users1.add(u4);
        users.add(u1);
        users.add(u2);
        redPacket.setUsers(users);
        //创建线程抢红包T1进入锁块后等待五秒
        Thread t1 = new Thread(new T1(redPacket,0));
        t1.start();
        Thread t2 = new Thread(new T2(redPacket,1));
        t2.start();
    }
}
