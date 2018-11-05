package com.example.demo.netty.chapter6;

import com.example.demo.pojo.UserInfo;
import io.netty.handler.codec.serialization.ClassResolvers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TestUserInfo {
    public static void main(String[] args) throws IOException {
        UserInfo info = new UserInfo();
        int loop = 100000;
        info.buildUserName("Welcome to Netty").buildUserID(100);
        long startTime = System.currentTimeMillis();
        for (int i=0; i < loop; i++){
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(info);
            oos.flush();
            oos.close();
            byte[] bytes = bos.toByteArray();bos.close();
//            System.out.println("JDK serializable :" + bytes.length);
            bos.close();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("JDK COST TIME: " + (endTime - startTime) + "ms");
        startTime = System.currentTimeMillis();
        for (int i = 0; i < loop; i++){
            byte[] bytes = info.codeC();
//            System.out.println("original size: " + info.codeC().length);
        }
        endTime = System.currentTimeMillis();
        System.out.println("original COST TIME: " + (endTime - startTime) + "ms");
    }
}
