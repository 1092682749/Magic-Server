package com.example.demo.netty;

import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class ReadRequest {
    public static String RL = "\r\n";
    public static BufferedReader reader;
    public static BufferedWriter writer;

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        // 监听8080
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept(); // 等待连接
//        获取输入输出流
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String str = null;
        String key = "";
        StringBuilder sb = new StringBuilder();
        while ((str = reader.readLine()) != null) {
            System.out.println(str);
            // 发现请求为websocket握手请求
            if (str.contains("Sec-WebSocket-Key")) {
                String[] strs = str.split(":");
                System.out.println(strs[1].trim());
                key = strs[1].trim();
//                发送websocket握手包
                System.out.println(reader.readLine());
                response(key, writer);
//                reader.skip()
//                break;
            }
            sb.append(str + "\r\n");
        }
        System.out.println(sb);
    }

    /**
     * 生成响应并发送
     * 生成过程：
     * 1.拼接key和固定字符串
     * 2.sha-1加密ps：加密过程以及算法本文不做研究
     * 3.拼接响应
     * 4.返回响应
     * @param key
     * @param writer
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static void response(String key,Writer writer) throws IOException, NoSuchAlgorithmException {
        key += "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
        //通过SHA-1算法进行更新
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(key.getBytes("utf-8"), 0, key.length());
        byte[] sha1Hash = md.digest();
        //进行Base64加密
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        key = encoder.encode(sha1Hash);
        StringBuilder responseSb = new StringBuilder();
        responseSb.append("HTTP/1.1 101 Switching Protocols").append(RL)
                .append("Upgrade: websocket").append(RL) // 必填且为固定应答
                .append("Connection: Upgrade").append(RL)
                .append("Sec-WebSocket-Accept: " + key).append(RL) //将生成的加密字符串返回
                .append("Sec-WebSocket-Version: 13").append(RL)
                .append(RL);
        writer.write(responseSb.toString());
        writer.flush();
//        writer.write("握手成功了");
//        writer.flush();

        if (reader != null) {
            new Thread() {
                @Override
                public void run() {
                    char[] bytes = new char[1024];
                    while (true) {
                        try {
                            int i = reader.read(bytes, 0, bytes.length);
                            System.out.println(i);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
    }

}
//class readClass extends Thread
