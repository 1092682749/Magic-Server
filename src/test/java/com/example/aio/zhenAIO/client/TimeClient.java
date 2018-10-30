package com.example.aio.zhenAIO.client;

import java.io.IOException;

public class TimeClient {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        new Thread(new AsyncTimeClientHandler("localhost", port),"AIO-AsyncTimeClientHandler-001").start();
    }
}
