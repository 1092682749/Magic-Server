package com.example.demo.P2P;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, P2PServer> {
    AsynchronousSocketChannel socketChannel;
    @Override
    public void completed(AsynchronousSocketChannel result, P2PServer attachment) {
        socketChannel = result;
        attachment.asyncServerChannel.accept(attachment, new AcceptHandler());
        System.out.println("handle\n");
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            InetSocketAddress address = (InetSocketAddress) result.getRemoteAddress();
            System.out.println(address.getClass());
            String hostname = address.getHostName();
            int port = address.getPort();
            buffer.putInt(hostname.length());
            buffer.put(hostname.getBytes());
            buffer.putInt(port);
            buffer.flip();
            result.write(buffer, buffer, new WriteHandler());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, P2PServer attachment) {

    }
    class WriteHandler implements CompletionHandler<Integer, ByteBuffer> {

        @Override
        public void completed(Integer result, ByteBuffer attachment) {
            if (attachment.hasRemaining()) {
                socketChannel.write(attachment, attachment, this);
            }
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {

        }
    }
}
