package com.example.demo.P2P;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

public class P2PServer {
    AsynchronousServerSocketChannel asyncServerChannel = AsynchronousServerSocketChannel.open();

    public P2PServer() throws IOException {
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(1);
        P2PServer server = new P2PServer();
        server.asyncServerChannel.bind(new InetSocketAddress(9898));
        server.asyncServerChannel.accept(server, new AcceptHandler());
        downLatch.await();
    }
}
