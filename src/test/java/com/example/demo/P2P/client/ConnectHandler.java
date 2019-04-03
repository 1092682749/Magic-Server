package com.example.demo.P2P.client;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class ConnectHandler implements CompletionHandler<Void, AsynchronousSocketChannel> {
    AsynchronousSocketChannel socketChannel;

    @Override
    public void completed(Void result, AsynchronousSocketChannel attachment) {
        socketChannel = attachment;
        System.out.println("connect\n");
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        attachment.read(buffer, buffer, new ReadHandler());
    }

    @Override
    public void failed(Throwable exc, AsynchronousSocketChannel attachment) {

    }

    class ReadHandler implements CompletionHandler<Integer, ByteBuffer> {

        @Override
        public void completed(Integer result, ByteBuffer attachment) {
            attachment.flip();

            int length = attachment.getInt();
            byte[] bytes = new byte[length];
            attachment.get(bytes);
            int port = attachment.getInt();
            System.out.println("port:"+ port + " host: " + new String(bytes));

            attachment.clear();
            socketChannel.read(attachment, attachment, this);
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {

        }
    }
}

