package com.example.demo.configration.netty;

import com.example.demo.configration.netty.copy.IHService;
import com.example.demo.configration.netty.copy.IWSService;
import com.example.demo.configration.netty.copy.WebSocketServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;

@Component
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        // 解码编码
        socketChannel.pipeline().addLast("http-codec", new HttpServerCodec());
        socketChannel.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
        socketChannel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
        socketChannel.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
        socketChannel.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
        socketChannel.pipeline().addLast(new CopyHander());
//        socketChannel.pipeline().addLast(new WebSocketServerHandler(new IWSService(),new IHService()));
    }
}
