package com.example.demo.P2P.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.CountDownLatch;

public class P2PClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(1);
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        socketChannel.bind(new InetSocketAddress( 9898));
        socketChannel.connect(new InetSocketAddress("dyzhello.club", 8989), socketChannel, new ConnectHandler());
        downLatch.await();
    }
}
