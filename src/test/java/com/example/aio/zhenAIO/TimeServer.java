package com.example.aio.zhenAIO;

public class TimeServer {
    public static void main(String[] args){
        int port = 8080;
        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        new Thread(timeServer, "AIO-AsyncimeServerHandler-001").start();
    }
}
