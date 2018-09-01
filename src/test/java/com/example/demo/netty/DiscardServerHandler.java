package com.example.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        super.channelReadComplete(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        try {
            // Do something with msg
            System.out.print(in.toString(io.netty.util.CharsetUtil.US_ASCII));
            String response = "HTTP/1.1  200  OK                        \r\n" +
                    "Server: Apache-Coyote/1.1                \r\n" +
                    "Content-Length: 24\r\n" +
                    "Date: Fri, 30 Jan 2015 01:54:57 GMT\r\n" +
                    "                                        \r\n" +
                    "this is hello servlet!!!  ";
            ctx.write(msg); // (1)
            ctx.flush(); // (2)
            } finally{
                ReferenceCountUtil.release(msg);
            }
        }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
