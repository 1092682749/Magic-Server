package com.example.demo.netty;

import org.apache.log4j.net.SocketServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Map;

public class OSocket {
    public static final String CRLF="\r\n";
    public static final String BLANK=" ";
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
                    String line = null;
                    while ((line = in.readLine()) != null) {
                        System.out.println(line);
                    }
                    Response response = new Response(socket.getOutputStream());
//                    response.println("<html><body>我是手写tomcat</body></html?");
                    response.pushToClient(200);
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
