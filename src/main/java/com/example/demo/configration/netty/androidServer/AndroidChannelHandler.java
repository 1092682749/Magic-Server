package com.example.demo.configration.netty.androidServer;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.ChatMsgRecord;
import com.example.demo.utils.json.JsonToBean;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class AndroidChannelHandler extends ChannelInboundHandlerAdapter {
    String webSocketUrl = "wss://dyzhello.club:8000";
    WebSocketServerHandshaker handshaker = null;

    public AndroidChannelHandler() {
        super();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("android专用");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("msg from android   " + msg.getClass());
        ChatMsgRecord chatMsgRecord = (ChatMsgRecord) JsonToBean.chagneObject(msg.toString(),ChatMsgRecord.class);
        System.out.println("消息发送者是====="+chatMsgRecord.getSendname());
        System.out.println("消息是====="+chatMsgRecord.getContent());
        chatMsgRecord.setReceivename("client1");
        chatMsgRecord.setSendname("server");
        chatMsgRecord.setContent("你好我服务器");
        ctx.writeAndFlush(JSON.toJSONString(chatMsgRecord));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
    public void handleHttp(ChannelHandlerContext ctx, Object msg) {
        FullHttpRequest request = (FullHttpRequest) msg;
        if (request.headers().get("Upgrade") != null){
            if (request.headers().get("Upgrade").equals("websocket")) {
                WebSocketServerHandshakerFactory handshakerFactory = new WebSocketServerHandshakerFactory(webSocketUrl,null,true);
                handshaker = handshakerFactory.newHandshaker(request);
                handshaker.handshake(ctx.channel(),request);
            }
        } else {
            // 处理普通http请求
            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
            response.headers().set(CONTENT_TYPE, "application/json; charset=UTF-8");
            StringBuilder bufRespose = new StringBuilder();
            bufRespose.append("该端口目前仅支持websocket协议\r\n");
            ByteBuf buffer = Unpooled.copiedBuffer(bufRespose, CharsetUtil.UTF_8);
            response.content().writeBytes(buffer);
            ChannelFuture future = ctx.channel().writeAndFlush(response);
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    ctx.close();
                }
            });
        }
    }

}
