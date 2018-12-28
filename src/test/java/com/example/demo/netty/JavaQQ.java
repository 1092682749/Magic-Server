package com.example.demo.netty;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JavaQQ {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9090);
        OutputStream out = socket.getOutputStream();
        new Thread() {
            @Override
            public void run() {
                try {
                    String msg = "";
                    InputStream in = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    while (true) {
                        msg = reader.readLine();
                        System.out.println(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        out.write("算是我收了\n".getBytes());
        out.flush();
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    out.write("你好呀\n".getBytes());
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 3, 3, TimeUnit.SECONDS);
    }
}
