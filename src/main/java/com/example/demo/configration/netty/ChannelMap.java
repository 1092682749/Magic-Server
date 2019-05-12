package com.example.demo.configration.netty;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.ChatMsgRecord;
import com.example.demo.model.User;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import io.netty.channel.Channel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

@Component
public class ChannelMap {
    @Autowired
    static RedisTemplate<String, Object> redisTemplate;
    private static final Logger log = Logger.getLogger(ChannelMap.class.getName());
    public static final ConcurrentHashMap<User, Channel> channelMap = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<String, StringBuilder> longFrameMap = new ConcurrentHashMap<>();
    public static void registerChannel(User registerUser, Channel channel) {
        log.info("新注册username：" + registerUser.getUsername());
        Set<User> users = ChannelMap.channelMap.keySet();
        ChatMsgRecord chatMsgRecord = new ChatMsgRecord();
        // 遍历所有已注册的channel
        for (User item : users) {
//            判断是否合一注册相同
            if (item.getUsername().equals(registerUser.getUsername())) {
                log.info("旧名字：" + item.getUsername());
                log.info("新名字：" + registerUser.getUsername());
                Channel removeChannel = channelMap.get(item);
                if (removeChannel.remoteAddress().equals(channel.remoteAddress())) {
                    System.out.println("equal对比");
                    System.out.println(removeChannel.equals(channel));
                    System.out.println("==对比：");
                    System.out.println(removeChannel == channel);
                    System.out.println("hash对比：");
                    System.out.println(removeChannel + "###########" + channel);
                    System.out.println("id对比：");
                    System.out.println(removeChannel.id()+"############"+channel.id());
                    System.out.println("同一地址不执行移出"+removeChannel.remoteAddress()+"####"+channel.remoteAddress());
                    return;
                }
                chatMsgRecord.setSendname("system");
                chatMsgRecord.setContent("重复登录连接已关闭");
//                系统给该用户发送重复登录提醒
                chatMsgRecord.setReceivename(item.getUsername());
                String jsonStr = JSON.toJSONString(chatMsgRecord);
                if (item.getAttachmentChannelType().equals("android")) {
                    removeChannel.writeAndFlush(jsonStr);
                } else {
                    removeChannel.writeAndFlush(new TextWebSocketFrame(jsonStr));
                }
            }
        }
        channelMap.put(registerUser, channel);

    }

}
