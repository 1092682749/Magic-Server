package com.example.demo.configration.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Component
public class NettyServerListener implements ServletContextListener {

    /**
     * 注入NettyServer
     */
    @Autowired
    private DiscardServer discardServer;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Thread thread = new Thread(new NettyServerThread());
        // 启动netty服务
        thread.start();
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

}
