package com.example.demo.netty;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ChineseTest {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        os.write("中文".getBytes("GBK"));
        byte[] readBuf = new byte[1024];
        int len = 0;
        while (true)
        {
            len = is.read(readBuf);
            System.out.println(new String(readBuf, "GBK"));
            if (false) break;
        }
        os.close();
        socket.close();
    }
}
