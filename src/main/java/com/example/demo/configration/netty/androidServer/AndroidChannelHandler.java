package com.example.demo.configration.netty.androidServer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.configration.netty.ChannelMap;
import com.example.demo.configration.netty.NettyConfig;
import com.example.demo.model.ChatMsgRecord;
import com.example.demo.model.User;
import com.example.demo.server.ChatMsgRecordService;
import com.example.demo.server.ChatMsgService;
import com.example.demo.server.UserService;
import com.example.demo.utils.json.JsonToBean;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;

import java.net.SocketAddress;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class AndroidChannelHandler extends ChannelInboundHandlerAdapter {
    String webSocketUrl = "wss://dyzhello.club:8000";
    WebSocketServerHandshaker handshaker = null;
    ChatMsgRecordService chatMsgRecordService = null;
    UserService userService = null;
    public AndroidChannelHandler() {
        super();
    }
    public AndroidChannelHandler(ChatMsgRecordService chatMsgRecordService, UserService userService) {
        this.chatMsgRecordService = chatMsgRecordService;
        this.userService = userService;
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
        NettyConfig.group.remove(ctx.channel());
        System.out.println("有一个链接被关闭");
        ConcurrentHashMap<User, Channel> channelMap = ChannelMap.channelMap;
        Set<Map.Entry<User, Channel>> set = channelMap.entrySet();
        for (Map.Entry entry : set) {
            Boolean isQ = ((Channel)entry.getValue()).id().asShortText().equals(ctx.channel().id().asShortText());
            System.out.println(isQ);
            if (isQ) {
                System.out.println("移除了一个android端channel");
                channelMap.remove(entry.getKey());
            }
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("msg from android   " + msg.toString());
        String requestStr = msg.toString();
        String[] ss =  requestStr.split("}\\{");
        // 如果注册请求和正常消息同时到达，放弃注册请求
        if (ss.length > 1) {
            requestStr = "{" + ss[1];
        }
        ChatMsgRecord chatMsgRecord = (ChatMsgRecord) JsonToBean.chagneObject(requestStr,ChatMsgRecord.class);
        System.out.println("消息发送者是====="+chatMsgRecord.getSendname());
        System.out.println("消息是====="+chatMsgRecord.getContent());
        User user = userService.findByUsername(chatMsgRecord.getSendname());
//        if (chatMsgRecord.getSendname().equals("qqq")){
//            System.out.println("xiang deng =================");
//        }
        if (user == null){
            System.out.println("user null");
        }
        user.setAttachmentChannelType("android");
        ChannelMap.registerChannel(user, ctx.channel());
        handleAndroidMsg(ctx,chatMsgRecord);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }
    // 处理安卓客户端消息
    private void handleAndroidMsg(ChannelHandlerContext ctx,ChatMsgRecord chatMsgRecord) {
        Set<User> users = ChannelMap.channelMap.keySet();
        chatMsgRecordService.save(chatMsgRecord);
        for (User user : users){
            if (user.getUsername().equals(chatMsgRecord.getReceivename())){
                Channel channel = ChannelMap.channelMap.get(user);
                if (user.getAttachmentChannelType().equals("android")){
                    channel.writeAndFlush(JSON.toJSONString(chatMsgRecord));
                } else {
                    channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(chatMsgRecord)));
                }
            }
        }
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
        System.out.println(cause.getMessage());
        ctx.close();
    }
    @SuppressWarnings("Duplicates")
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
