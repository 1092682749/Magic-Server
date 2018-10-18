package com.example.demo.netty;
//客户端

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class MyClient extends JFrame implements ActionListener, Runnable {

    Socket clientSocket;
    JTextArea jTextReceive = new JTextArea();
    JTextField jTextSend = new JTextField();
    JButton jbc = new JButton("发送");
    JScrollPane jsp = new JScrollPane(jTextReceive);
    boolean flag = true;                   //标记是否结束
    Thread connenThread;                 //用于向服务器端发送信息
    DataInputStream cin1;
    DataOutputStream cout;
    static boolean flagclient = false;

    public static void main(String[] args) {
        new MyClient().clientStart();
    }

    public synchronized void changeFlag(boolean t) {
        flagclient = t;
    }

    public void clientStart() {
        try {                              //连接服务器端，这里使用本机
            this.setTitle("客户端，端口号:8080");
            this.setLayout(null);
            this.setBounds(20, 00, 300, 300);
            jsp.setBounds(20, 20, 220, 100);
            jTextSend.setBounds(20, 120, 220, 50);
            jbc.setBounds(20, 170, 220, 30);
            this.add(jsp);
            this.add(jTextSend);
            this.add(jbc);
            this.setVisible(true);
            jbc.addActionListener(this);
            clientSocket = new Socket("localhost", 9000);
            jTextReceive.setText("连接已经建立完毕!\n");
            while (flag) {
                try (InputStream is = clientSocket.getInputStream()) {
                    cin1 = new DataInputStream(clientSocket.getInputStream());
                    try (OutputStream os = clientSocket.getOutputStream()) {
                        cout = new DataOutputStream(os);
                        connenThread = new Thread(this);
                        connenThread.start();     //启动线程，向服务器端发送信息
                        String aLine;
                        while (true) {
                            aLine = cin1.readUTF();
                            jTextReceive.append("服务器发来信息：" + aLine + "\n");
                            if (aLine.equals("bye")) {
                                flag = false;
                                connenThread.interrupt();
                                break;
                            }
                        }
                        cout.close();
                    }
                    cin1.close();
                }
                clientSocket.close();    //关闭Socket连接
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (flagclient == true) {

                    String str = jTextSend.getText().trim();
                    if(str==null||str.length()<=0){}
                    else{
                        jTextReceive.append("发送消息:" + str + "\n");
                        cout.writeUTF(str);
                        cout.flush();}
                    jTextSend.setText(null);
                    changeFlag(false);
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jbt = (JButton) e.getSource();
        if (jbt == jbc) //若单击确认对话框中的“发送”按钮
        {
            changeFlag(true);
        }
    }
}




