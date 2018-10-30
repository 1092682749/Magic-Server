package com.example.aio.nio;

public class TimeServer {
    public static void main(String[] args){
        int port = 8080;
        MultiplierTimeServer server = new MultiplierTimeServer(port);
        new Thread(server,"Nio-MultiplierTimeServer-001").start();
    }
}
