package com.example.aio.zhenAIO.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AsyncTimeClientHandler implements Runnable {
    private AsynchronousSocketChannel client;
    private String host;
    private int port;
    private CountDownLatch latch;
    public AsyncTimeClientHandler(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        client = AsynchronousSocketChannel.open();
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        client.connect(new InetSocketAddress(host, port));
        char[] c = new char[1024];
        byte[] bytes = new byte[1024];

    }
}
