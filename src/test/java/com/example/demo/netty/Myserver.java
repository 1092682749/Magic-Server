package com.example.demo.netty;

//服务器端


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Myserver extends JFrame implements ActionListener, Runnable {
    JTextArea jTextReceive = new JTextArea();
    JTextField jTextSend = new JTextField();
    JButton jbs = new JButton("发送");
    ServerSocket server = null;
    JScrollPane jsp = new JScrollPane(jTextReceive);
    Socket clientSocket;     //负责当前线程中C/S通信中的Socket对象
    boolean flag = true;       //标记是否结束
    Thread connenThread;     //向客户端发送信息的线程
    BufferedReader sin;      //输入流对象
    DataInputStream sin1;
    DataOutputStream sout;   //输出流对象
    boolean flags = false;

    public static void main(String[] args) {
        Myserver MS = new Myserver();
        MS.serverStart();
    }

    public synchronized void changeFlag(boolean t) {
        flags = t;
    }


    public void serverStart() {
        try {
            server = new ServerSocket(9000);    //建立监听服务
            this.setTitle("服务器端，端口号:" + server.getLocalPort());
            this.setLayout(null);
            this.setBounds(20, 00, 300, 300);
            jsp.setBounds(20, 20, 220, 100);
            jTextSend.setBounds(20, 120, 220, 50);
            jbs.setBounds(20, 170, 220, 30);
            this.add(jsp);
            this.add(jTextSend);
            this.add(jbs);
            this.setVisible(true);
            jbs.addActionListener(this);
            while (flag) {
                clientSocket = server.accept();
                jTextReceive.setText("连接已经建立完毕!\n");
                try (InputStream is = clientSocket.getInputStream()) {
                    sin = new BufferedReader(new InputStreamReader(is));
                    try (OutputStream os = clientSocket.getOutputStream()) {
                        sin1 = new DataInputStream(clientSocket.getInputStream());
                        sout = new DataOutputStream(os);
                        connenThread = new Thread(this);
                        connenThread.start();     //启动线程，向客户端发送信息
                        String aLine;
                        while (true) {
                            aLine = sin1.readUTF();
                            jTextReceive.append("客户端发来信息：" + aLine + "\n");
                            if (aLine.equals("bye")) {
                                flag = false;
                                connenThread.interrupt();
                                break;
                            }
                        }
                        sout.close();
                    }
                    sin.close();
                }
                clientSocket.close();     //关闭Socket连接
                System.exit(0);          //程序运行结束
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
                Logger.getLogger(Myserver.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (flags == true) {
                    changeFlag(false);
                    String str = jTextSend.getText().trim();
                    if (str == null || str.length() <= 0) {
                    } else {
                        sout.writeUTF(str);
                        sout.flush();
                        jTextReceive.append("服务器发送消息:" + str + "\n");
                    }
                    jTextSend.setText("");
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void finalize() //析构方法
    {
        try {
            server.close();
        } //停止ServerSocket服务
        catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jbt = (JButton) e.getSource();
        if (jbt == jbs) //若单击确认对话框中的“发送”按钮
        {
            changeFlag(true);
        }
    }
}
