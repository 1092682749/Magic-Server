package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.Protocol;
import com.example.demo.utils.json.JsonToBean;

public class ProtocolToObject {
    public static Protocol convert(String ps){
        Protocol protocol = new Protocol();
//        String body = ps.split("Host")[1];

//        protocol = (Protocol) JsonToBean.chagneObject(body,Protocol.class);
        String[] oneline = ps.split("/");
        protocol.setMethod(oneline[0].trim());
        protocol.setProtocolName(oneline[1].trim());
        protocol.setProtocolVersion(oneline[2].split("\r\n")[0].trim());
        String[] bodys = ps.split("\r\n");
        for (int i = 0; i < bodys.length; i++) {
            String[] lineArr = null;
            try{
                lineArr = bodys[i].split(":");
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("抛出异常时的i是："+i);
            }
            if (lineArr[0].equals("Host")){
                protocol.setHost(lineArr[1].trim());
            }
            if (lineArr[0].equals("Connection")){
                protocol.setConnection(lineArr[1].trim());
            }
            if (lineArr[0].equals("Cache-Control")){
                protocol.setCacheControl(lineArr[1].trim());
            }
            if (lineArr[0].equals("Upgrade-Insecure-Requests")){
                protocol.setUpGradeInsecureRequest(lineArr[1].trim());
            }
            if (lineArr[0].equals("User-Agent")){
                protocol.setUserAgent(lineArr[1].trim());
            }
            if (lineArr[0].equals("Accept")){
                protocol.setAccept(lineArr[1].trim());
            }
            if (lineArr[0].equals("Accept-Encoding")){
                protocol.setAcceptEncoding(lineArr[1].trim());
            }
            if (lineArr[0].equals("User-Agent")){
                protocol.setUserAgent(lineArr[1].trim());
            }
            if (lineArr[0].equals("Accept-Language")){
                protocol.setAcceptLanguage(lineArr[1].trim());
            }
            if (lineArr[0].equals("Cookie")){
                protocol.setCookie(lineArr[1].trim());
            }
        }
        return protocol;
    }
}
