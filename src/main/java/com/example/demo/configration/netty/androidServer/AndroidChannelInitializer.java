package com.example.demo.configration.netty.androidServer;

import com.example.demo.service.ChatMsgRecordService;
import com.example.demo.service.UserService;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import java.io.InputStream;
import java.security.KeyStore;;

@Component
public class AndroidChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Autowired
    UserService userService;
    @Autowired
    ChatMsgRecordService chatMsgRecordService;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        KeyStore ks = KeyStore.getInstance("JKS");
        InputStream ksInputStream = AndroidChannelInitializer.class.getClassLoader().getResourceAsStream("dyzhello.club.jks");
        ks.load(ksInputStream, "109268".toCharArray());
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, "109268".toCharArray());
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), null, null);
        SSLEngine sslEngine = sslContext.createSSLEngine();
        sslEngine.setUseClientMode(false); //服务器端模式
        sslEngine.setNeedClientAuth(false); //不需要验证客户端
//        ChannelPipeline p = ch.pipeline();
//        SslContext sslCtx = SslContextBuilder.forServer(AndroidChannelInitializer.class.getClassLoader().getResourceAsStream("server_ks.jks"),AndroidChannelInitializer.class.getClassLoader().getResourceAsStream("serverTrust_ks.jks")).build();
//        p.addLast("ssl", sslCtx.newHandler(ch.alloc()));
        // ssl
        ch.pipeline().addLast(new SslHandler(sslEngine));
        ch.pipeline().addLast(new StringEncoder());
        ch.pipeline().addLast(new StringDecoder());
//        ch.pipeline().addLast(new ObjectDecoder(new ClassResolver() {
//            @Override
//            public Class<?> resolve(String s) throws ClassNotFoundException {
//                return Class.forName(s);
//            }
//        }));
//        ch.pipeline().addLast(new ObjectEncoder());
        ch.pipeline().addLast(new AndroidChannelHandler(chatMsgRecordService,userService));
    }
}
