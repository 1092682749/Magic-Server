package com.example.demo.netty;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TCPWrite {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        OutputStream os = socket.getOutputStream();
        FileInputStream is = new FileInputStream("C:\\Users\\dyz\\Desktop\\PDF\\重构-改善既有代码的设计2010版.pdf");
        byte[] outBuf = new byte[1024];
        int len;
        os.write("重构-改善既有代码的设计2010版.pdf".getBytes("GBK"));
        do {
            len = is.read(outBuf, 0, 1024);
            if (len > 0) {
                os.write(outBuf, 0, len);
                os.flush();
            }
        } while (len > 0);
        os.close();
        socket.close();
    }
}
