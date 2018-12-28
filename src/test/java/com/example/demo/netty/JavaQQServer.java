package com.example.demo.netty;



import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class JavaQQServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9090);
        while (true) {
            Socket socket = serverSocket.accept();
            new ReaderT(socket).start();
        }

    }
}
class ReaderT extends Thread {
    Socket socket;
    ReaderT(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            System.out.println("接受到一个请求");
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String msg = "";
            while (true) {
                msg = reader.readLine();
                OutputStream out = socket.getOutputStream();
                msg += "还给你\n";
                out.write(msg.getBytes());
                out.flush();
                System.out.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}