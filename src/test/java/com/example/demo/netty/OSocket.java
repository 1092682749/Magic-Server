package com.example.demo.netty;

import org.apache.log4j.net.SocketServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class OSocket {
    public void server() throws IOException {
        ServerSocket socketServer = new ServerSocket(9000);
        while(true) {
            Socket socket = socketServer.accept();
            System.out.println("connect");
            new Thread(this.new SocketHandler(socket)).start();
        }
    }

    class SocketHandler implements Runnable {
        Socket socket = null;
        BufferedWriter out = null;
        BufferedReader in = null;
        Boolean isConnecting = false;
        SocketHandler(Socket socket) {
            this.socket = socket;
            isConnecting = true;
        }

        @Override
        public void run() {
            while (isConnecting) {
                try {
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    String msg = in.readLine();
                    System.out.println(msg);
                    out.write("服务器把你的消息还给了你"+ msg);
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        out.close();
                        in.close();
                        isConnecting = false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        OSocket oSocket = new OSocket();
        oSocket.server();
    }
}
