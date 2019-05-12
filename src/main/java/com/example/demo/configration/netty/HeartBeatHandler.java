package com.example.demo.configration.netty;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.ChatMsgRecord;
import com.example.demo.utils.json.JsonToBean;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@ChannelHandler.Sharable
@Component
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {
    static Logger logger = Logger.getLogger(HeartBeatHandler.class.getName());
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("heart" + msg.getClass().getName());
        boolean heart = false;
        if (msg instanceof String) {
           if (((String) msg).contains("nccHeart")) {
               heart = true;
               ChatMsgRecord record = new ChatMsgRecord();
               record.setType(ChatMsgRecord.TYPE_HEART_BEAT);
               ctx.writeAndFlush(JSON.toJSONString(record));
           }

        } else if (msg instanceof WebSocketFrame) {
            TextWebSocketFrame wsf = (TextWebSocketFrame) msg;
            if (wsf.text().contains("nccHeart")) {
                heart = true;
                ChatMsgRecord record = new ChatMsgRecord();
                record.setType(ChatMsgRecord.TYPE_HEART_BEAT);
                ctx.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(record)));
            }
        }
        if (!heart) {
            ctx.fireChannelRead(msg);
        }
    }
}
