package com.example.demo.controller;


import com.example.demo.utils.ConstantMap;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.*;

// @Controller
public class IPConnectController {


    IPConnectController() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(7777);
                    while (true) {
                        Socket socket = serverSocket.accept();
                        HandleSocket handleSocket = new HandleSocket(socket);
                        new Thread(handleSocket).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @RequestMapping("/getRequest")
    public void getIP(HttpServletRequest request) {
        String addr = request.getRemoteAddr();

        System.out.println(addr);
    }
}

class HandleSocket implements Runnable {
    Socket socket;
    BufferedInputStream inputStream;
    BufferedOutputStream outputStream;

    HandleSocket(Socket socket) throws IOException {
        this.socket = socket;
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        inputStream = new BufferedInputStream(is);
        outputStream = new BufferedOutputStream(os);

    }

    @Override
    public void run() {
        try {

            byte[] readBuf = new byte[1024];
            byte b = 0;
            Arrays.fill(readBuf, b);

            int number = inputStream.read(readBuf);


            InetAddress address = socket.getInetAddress();
            String host = address.getHostName();
            String name = new String(readBuf, 0, number, "UTF-8");
            name.trim();

            ConstantMap.ipMap.put(name, socket);
            System.out.println("put name is: " + name + " length is: " + name.length());
            outputStream.write(host.getBytes());
            outputStream.flush();
            notifySocket();
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void notifySocket() throws IOException, InterruptedException {
        byte[] bytes = new byte[1024];
        byte b = 0;
        Arrays.fill(bytes, b);
        while (true) {

            int number = inputStream.read(bytes);
            String message = new String(bytes, 0, number,  "UTF-8");

            String[] ss = message.split(" ");
            if (ss[0].equals("GET")) {
                String needName = ss[1];
                needName.trim();
                System.out.println(needName.equals("A"));
                Socket needSocket = ConstantMap.ipMap.get(needName);

                System.out.println("name is: " + needName + "length is:" + needName.length());
                System.out.println("Socket is: " + needSocket);
                String hostname = needSocket.getInetAddress().getHostName();
                System.out.println(hostname);
                outputStream.write(hostname.getBytes());
                outputStream.flush();
        }
    }
}
}
class Name {
    String username;
    Name(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Name)) return false;
        Name o = (Name) obj;
        return o.username.equals(username);
    }
}
