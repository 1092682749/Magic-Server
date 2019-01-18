package com.example.aio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {
    public static void main(String[] args){
        int port = 8080;
        if (args != null && args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                System.out.println("格式非数字");
            }
        }
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(port);
            System.out.println("port is " + port);
            Socket socket1 = null;
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50,10000);
            while (true){
                socket1 = socket.accept();
                singleExecutor.execute(new TimeServerHandler(socket1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (socket != null){
                System.out.println("service close");
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
