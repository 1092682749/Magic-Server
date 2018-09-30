package com.example.demo.configration.netty;

import com.example.demo.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import io.netty.channel.Channel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Configuration
public class ChannelMap {
    public static final ConcurrentHashMap<User, Channel> channelMap = new ConcurrentHashMap<>();
}
