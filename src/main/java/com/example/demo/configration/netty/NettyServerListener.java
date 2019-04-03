package com.example.demo.configration.netty;

import com.example.demo.configration.netty.androidServer.AndroidNettyServer;
import com.example.demo.configration.netty.webServer.DiscardServer;
import com.example.demo.configration.p2p.P2PServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WebListener
public class NettyServerListener implements ServletContextListener {
    static public ExecutorService threadPool = Executors.newCachedThreadPool();
    /**
     * 注入NettyServer
     */
    @Autowired
    private DiscardServer discardServer;
    @Autowired
    private AndroidNettyServer androidNettyServer;
    @Autowired
    private P2PServer p2PServer;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        Thread webNetty = new Thread(new NettyServerThread());
        webNetty.start();
        Thread androidNetty = new Thread(new AndroidServerThread());
        androidNetty.start();
        Thread p2pThread = new Thread(p2PServer);
        System.out.println("====================");
        p2pThread.start();
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
