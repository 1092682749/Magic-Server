package com.example.demo.configration.netty;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.ChatMsg;
import com.example.demo.model.ChatMsgRecord;
import com.example.demo.model.User;
import com.example.demo.server.ChatMsgRecordService;
import com.example.demo.server.ChatMsgService;
import com.example.demo.server.UserService;
import com.example.demo.utils.json.JsonToBean;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.nio.channels.Channel;
import java.util.Date;
import java.util.Set;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class ChatHandler extends ChannelInboundHandlerAdapter {
    String webSocketUrl = "wss://dyzhello.club:8080";
    WebSocketServerHandshaker handshaker = null;
    ChatMsgRecordService chatMsgRecordService = null;
    UserService userService = null;
    public ChatHandler(ChatMsgRecordService chatMsgRecordService,UserService userService) {
        super();
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
        NettyConfig.group.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.remove(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            // 处理http请求
            handleHttp(ctx,msg);
            return;
        }   else if (msg instanceof WebSocketFrame) {
            // 处理websocket帧
            if (msg instanceof CloseWebSocketFrame){
                handshaker.close(ctx.channel(), ((CloseWebSocketFrame) msg).retain());
                return;
            } else if (msg instanceof  PingWebSocketFrame) {
                ctx.channel().write(new PongWebSocketFrame(((PingWebSocketFrame)msg).content().retain()));
            }
            MsgObject msgObject = (MsgObject) JsonToBean.chagneObject(((TextWebSocketFrame) msg).text(),MsgObject.class);
            User user = new User();
            user.setUsername(msgObject.getUsername());
            ChannelMap.channelMap.put(user,  ctx.channel());
            handleWebSocket(ctx,msgObject);
        }else {
            ChannelFuture f = ctx.channel().writeAndFlush("该端口目前仅支持websocket协议\r\n");
            f.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    ctx.close();
                }
            });
        }
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
    public void handleWebSocket(ChannelHandlerContext ctx, MsgObject msg) throws Exception {

        if (msg.getReceivename().equals("") || msg.getReceivename() == null) {
//            ctx.channel().writeAndFlush(new TextWebSocketFrame("您没有指定发送目标，本条消息原物返还"));
        }else if (msg.getReceivename().equals("all")){
            NettyConfig.group.writeAndFlush(new TextWebSocketFrame(msg.getMsg()+" (该消息广播了"+NettyConfig.group.size()+"位用户)"));
        } else {
            String[] DPS = msg.getMsg().split("/");
            if (DPS[0].equals("DS") || DPS.equals("DE")){
                // 处理超长frame
                HandleLongFrame(ctx,msg,DPS);
            }
            sendMessageToReceive(ctx,msg);
        }
    }
    public void sendMessageToReceive(ChannelHandlerContext ctx, MsgObject msg) throws Exception {
        Set<User> userSet = ChannelMap.channelMap.keySet();
        ChatMsgRecord chatMsgRecord = saveMsg(msg);
        for (User ruser : userSet){
            if (ruser.getUsername().equals(msg.getReceivename())) {
                String jsonStr = JSON.toJSONString(chatMsgRecord);
                ChannelMap.channelMap.get(ruser).writeAndFlush(new TextWebSocketFrame(jsonStr));
            }
        }

//        ctx.channel().writeAndFlush(new TextWebSocketFrame("用户不存在或者不在线"));
    }
    public ChatMsgRecord saveMsg(MsgObject msg) throws Exception {
        String[] msgs = msg.msg.split("dyz/");
        ChatMsgRecord chatMsgRecord = new ChatMsgRecord();
        Date date = new Date();
        chatMsgRecord.setAddtime(date);
        chatMsgRecord.setContent(msg.getMsg());
        chatMsgRecord.setSendname(msg.username);
        chatMsgRecord.setReceivename(msg.receivename);
        chatMsgRecord.setType(1);
        if (msgs[0].equals("data:audio")){
            chatMsgRecordService.deleteRecord(msg.getUsername(),msg.getReceivename());
        }
        chatMsgRecordService.save(chatMsgRecord);
        return chatMsgRecord;
    }
    void HandleLongFrame(ChannelHandlerContext ctx,MsgObject msg,String[] DPS) throws Exception {
        StringBuilder newMsg = new StringBuilder();
        for (int i = 1; i < DPS.length; i++) {
            newMsg.append(DPS[i]);
        }
        StringBuilder oldMsg = ChannelMap.longFrameMap.get(msg.getUsername());
        if (oldMsg == null) {
            ChannelMap.longFrameMap.put(msg.getUsername(),newMsg);
        }else if (DPS[0].equals("DS")) {
            oldMsg.append(newMsg);
        } else if (DPS[0].equals("DE")) {
            oldMsg.append(newMsg);
            msg.setMsg(oldMsg.toString());
            ChannelMap.longFrameMap.remove(msg.getUsername());
            sendMessageToReceive(ctx,msg);
        }
    }
}
