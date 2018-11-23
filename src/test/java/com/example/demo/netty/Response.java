package com.example.demo.netty;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

public class Response {
    public static final String CRLF="\r\n";
    public static final String BLANK=" ";
    private BufferedWriter bw;
    private StringBuilder headInfo;
    private StringBuilder contentText;
    private int len;
    private Response()
    {
        headInfo = new StringBuilder();
        contentText = new StringBuilder();
    }
    Response(Socket Client)
    {
        this();
        try {
            bw = new BufferedWriter(new OutputStreamWriter(Client.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Response(OutputStream outputStream)
    {
        this();
        bw = new BufferedWriter(new OutputStreamWriter(outputStream));
    }
    public void creatHradInfo(int code)
    {
        headInfo.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
        switch(code)
        {
            case 200:
                headInfo.append("OK");
                break;
            case 400:
                headInfo.append("NOT FOUND");
                break;
            case 505:
                headInfo.append("SEVER ERROR");
                break;
        }
        headInfo.append(CRLF);
        headInfo.append("Server:DYZMode/Server/0.01").append(CRLF);
        headInfo.append("Date").append(new Date()).append(CRLF);
//        headInfo.append("Content-type:text/html;charset=GBK").append(CRLF);
        headInfo.append("Content-type:text/html;charset=utf-8").append(CRLF);
        headInfo.append("Content-Length").append(len).append(CRLF);
        headInfo.append(CRLF);
    }
    public Response print(String str)
    {
        contentText.append(str);
        len += str.length();
        return this;
    }
    public Response println(String str)
    {
        contentText.append(str).append(CRLF);
        len += str.length();
        return this;
    }
    public void pushToClient(int code)
    {
        if (null==headInfo)
        {
            code=500;
        }
        creatHradInfo(code);

        try {
            bw.append(headInfo);
            bw.append(contentText);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void close()
    {
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
