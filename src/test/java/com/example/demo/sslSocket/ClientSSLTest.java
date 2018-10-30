package com.example.demo.sslSocket;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientSSLTest {

    static String keystorePath = "client_ks.jks";
    static String trustKeystorePath = "serverTrust_ks.jks";
    static String keystorePassword = "client";
    public static void main(String args[]) throws Exception{
        //System.setProperty("javax.net.debug", "ssl,handshake");
        System.setProperty("javax.net.ssl.keyStore", ClientSSLTest.class.getClassLoader().getResource(keystorePath).getPath());
        System.setProperty("javax.net.ssl.keyStorePassword", keystorePassword);
        System.setProperty("javax.net.ssl.trustStore",  ClientSSLTest.class.getClassLoader().getResource(trustKeystorePath).getPath());
        System.setProperty("javax.net.ssl.trustStorePassword",keystorePassword);

        SocketFactory factory = SSLSocketFactory.getDefault();
        Socket sslsocket = factory.createSocket("127.0.0.1", 9100);
        System.out.println("Client Connected");
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sslsocket.getOutputStream(),"UTF-8"));
        out.write("This is message：你好"+"\n");
        out.flush();
        System.out.println("Msg Sent");
        sslsocket.close();
    }
}
