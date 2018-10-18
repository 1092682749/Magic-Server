package com.example.demo.configration.netty;

import com.example.demo.configration.netty.androidServer.AndroidNettyServer;
import com.example.demo.configration.netty.webServer.DiscardServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Component
public class NettyServerListener implements ServletContextListener {

    /**
     * 注入NettyServer
     */
    @Autowired
    private DiscardServer discardServer;
    @Autowired
    private AndroidNettyServer androidNettyServer;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Thread webNetty = new Thread(new NettyServerThread());
        webNetty.start();
        Thread androidNetty = new Thread(new AndroidServerThread());
        androidNetty.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    /**
     * netty服务启动线程
     */
    private class NettyServerThread implements Runnable {

        @Override
        public void run() {
            try {
                discardServer.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private class AndroidServerThread implements Runnable {

        @Override
        public void run() {
            try {
                androidNettyServer.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
