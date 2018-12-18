package com.example.demo.configration.netty.webServer;

import com.example.demo.configration.netty.webServer.ChatHandler;
import com.example.demo.server.ChatMsgRecordService;
import com.example.demo.server.UserService;
import com.example.demo.utils.SslUtil;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import java.util.concurrent.TimeUnit;

@Component
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Autowired
    ChatMsgRecordService chatMsgRecordService;
    @Autowired
    UserService userService;
    Boolean startTls = true;
    public ServerChannelInitializer() throws Exception {
    }
    public ServerChannelInitializer(Boolean startTls) {
        this.startTls = startTls;
    }
    @Override
    protected void initChannel(SocketChannel socketChannel)  {
        SSLContext sslContext = null;
        String path = "";
        MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        try {
            path = "/dyzhello.club.jks";
            sslContext = SslUtil.createSSLContext("JKS", path, "109268");
//            sslContext = SecureChatSslContextFactory.getServerContext();
//            sslContext = SslUtil.createSSLContext("JKS",getClass().getClassLoader().getResource("dyzhello.club.jks").getPath(),"109268");
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println(path);
        }
        //SSLEngine 此类允许使用ssl安全套接层协议进行安全通信
        SSLEngine engine = sslContext.createSSLEngine();
        engine.setUseClientMode(false);
        engine.setNeedClientAuth(false);
//        socketChannel.pipeline().addLast(new AndroidChannelHandler());
//        socketChannel.pipeline().addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS));
//        socketChannel.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
//        socketChannel.pipeline().addLast("frameEncoder", new LengthFieldPrepender(4));  //最大16M
        socketChannel.pipeline().addLast(new SslHandler(engine));
        socketChannel.pipeline().addLast("http-codec", new HttpServerCodec());
        socketChannel.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
        socketChannel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
        socketChannel.pipeline().addLast(new StringDecoder());
        socketChannel.pipeline().addLast(new StringEncoder());
//        socketChannel.pipeline().addLast(new MarshallingDecoder(new DefaultUnmarshallerProvider(marshallerFactory,configuration)));
//        socketChannel.pipeline().addLast(new MarshallingEncoder(new DefaultMarshallerProvider(marshallerFactory,configuration)));
//        socketChannel.pipeline().addLast(new AcceptorIdleStateTrigger());
//        socketChannel.pipeline().addLast(new CopyHander());
//        socketChannel.pipeline().addLast(new WebSocketServerHandler(new IWSService(),new IHService()));
//        socketChannel.pipeline().addLast(new TimeServerHandler());
        socketChannel.pipeline().addLast(new IdleStateHandler(10,0,0, TimeUnit.SECONDS));
        socketChannel.pipeline().addLast(new ChatHandler(chatMsgRecordService,userService));
    }
}
