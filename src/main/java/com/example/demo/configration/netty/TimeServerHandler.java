package com.example.demo.configration.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import org.springframework.stereotype.Component;
//@ChannelHandler.Sharable
//@Component
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
        System.out.println(msg);
//        if (msg instanceof FullHttpRequest) {
//            // http请求
//            WebSocketServerHandshakerFactory handshakerFactory = new WebSocketServerHandshakerFactory("ws://locahost:8080",null,false);
//            WebSocketServerHandshaker handshaker = handshakerFactory.newHandshaker((HttpRequest) msg);
//            handshaker.handshake(ctx.channel(),(HttpRequest) msg);
//        } else if (msg instanceof WebSocketFrame) {
//            ctx.channel().writeAndFlush(new TextWebSocketFrame("已阅"));
//        }
        String response = "HTTP/1.1 200 OK\n" +
                "Server: Tengine\n" +
                "Date: Tue, 25 Sep 2018 09:53:54 GMT\n" +
                "Content-Type: application/octet-stream\n" +
                "Transfer-Encoding: chunked\n" +
                "Connection: close\n" +
                "Vary: Accept-Encoding\n" +
                "X-Powered-By: ring/1.0.0\n" +
                "gsid: 011138152241153786923440700028770464940\n" +
                "sc: 0.005\n" +
                "Access-Control-Allow-Origin: *\n" +
                "Access-Control-Allow-Methods: *\n" +
                "Access-Control-Allow-Headers: DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,key,x-biz,x-info,platinfo,encr,enginever,gzipped,poiid\n" +
                "Content-Encoding: gzip\n";
        final ChannelFuture f = ctx.writeAndFlush(response); // (3)
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                ctx.close();
            }
        }); // (4)
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
