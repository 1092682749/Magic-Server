package com.example.aio.nio;


public class TimeClient {
    public static void main(String[] args){
        int port = 8080;
        new Thread(new TimeClientHandler("localhost",port),"TimeClient-001").start();
    }
}
