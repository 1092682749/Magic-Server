package com.example.demo.sslSocket;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerSSLTest {
    static String keystorePath = "server_ks.jks";
    static String trustKeystorePath = "clientTrust_ks.jks";
    static String keystorePassword = "server";
    public static void main(String args[]) throws Exception{
        //System.setProperty("javax.net.debug", "ssl,handshake");
        System.out.println(ServerSSLTest.class.getClassLoader().getResource(keystorePath).getPath());
        System.setProperty("javax.net.ssl.keyStore", ServerSSLTest.class.getClassLoader().getResource(keystorePath).getPath());
        System.setProperty("javax.net.ssl.keyStorePassword", keystorePassword);
        System.setProperty("javax.net.ssl.trustStore", ServerSSLTest.class.getClassLoader().getResource(trustKeystorePath).getPath());
        System.setProperty("javax.net.ssl.trustStorePassword",keystorePassword);
        ServerSocketFactory factory =  SSLServerSocketFactory.getDefault();
        SSLServerSocket serverSocket=(SSLServerSocket) factory.createServerSocket(9100);
        serverSocket.setNeedClientAuth(true);
        System.out.println("Server Open");
        Socket socket =serverSocket.accept();
        BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
        System.out.println("Server Log:"+in.readLine());
    }
}
