package com.example.aio.zhenAIO;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;

public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
    private AsynchronousSocketChannel asynchronousSocketChannel;
    public ReadCompletionHandler(AsynchronousSocketChannel channel) {
        if (this.asynchronousSocketChannel == null) {
            asynchronousSocketChannel = channel;
        }
    }
    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        byte[] body = new byte[attachment.remaining()];
        attachment.get(body);
        try {
            String req = new String(body, "utf-8");
            String currentTime = "QUERY TIME ORDER".equals(req) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
            doWrite(req);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    private void doWrite(String currentTime) {
        if (currentTime != null && currentTime.length() > 0) {
            byte[] bytes = currentTime.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            asynchronousSocketChannel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    if (attachment.hasRemaining()) {
                        asynchronousSocketChannel.write(attachment, attachment, this);
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        asynchronousSocketChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            asynchronousSocketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
