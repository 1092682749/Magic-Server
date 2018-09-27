package com.example.demo.configration.netty;

import com.example.demo.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import io.netty.channel.Channel;
import java.util.HashMap;
import java.util.Map;

//@Configuration
public class ChannelMap {
    public static final Map<User, Channel> channelMap = new HashMap<>();
//    @Bean
//    public static final Map<User, Channel> getChannelMap() {
//        return new HashMap<>();
//    }
}
