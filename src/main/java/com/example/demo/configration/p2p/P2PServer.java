package com.example.demo.configration.p2p;

import com.alibaba.fastjson.JSON;
import com.example.demo.configration.netty.NettyServerListener;
import com.example.demo.utils.json.JsonToBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class P2PServer implements Runnable {
    Logger logger = (Logger) Logger.getLogger(P2PServer.class.getName());
    static public Map<String, PeerInfo> userList = new HashMap<>();
    int p2pServerPort = 7777;

    @Override
    public void run() {
        try {
            DatagramPacket datagramPacket = new DatagramPacket(new byte[1024], 1024);
            DatagramSocket serverDataSocket = new DatagramSocket(p2pServerPort);
            System.out.println("p2pServer start!");
            while (true) {

                serverDataSocket.receive(datagramPacket);


                String message = new String(datagramPacket.getData(), 0, datagramPacket.getLength(), "utf-8");
                PeerMessage peerMessage = (PeerMessage) JsonToBean.chagneObject(message, PeerMessage.class);


                if (peerMessage.type.equals(PeerMessage.TYPE_ASSIST)) { // 表示有客户端需要协助打洞

                    PeerInfo peerInfo = userList.get(peerMessage.getDestinationName());

                    if (peerInfo == null) {
                        String response = "该用户不在线，或不存在该用户\r\n";
                        PeerResponseMessage responseMessage = new PeerResponseMessage();
                        responseMessage.setMsg(response);
                        String responseJson = JSON.toJSONString(responseMessage);
                        DatagramPacket responsePacket = new DatagramPacket(responseJson.getBytes(),
                                responseJson.length(), datagramPacket.getSocketAddress());
                        serverDataSocket.send(responsePacket);
                    } else {
                        PeerResponseMessage responseMessage = new PeerResponseMessage();
                        responseMessage.setMsg("destinationInfo");
                        responseMessage.setDestinationIP(peerInfo.natIP);
                        responseMessage.setDestinationPort(peerInfo.port);
                        String responseJson = JSON.toJSONString(responseMessage);
                        // 把目的主机的ip和端口返回
                        serverDataSocket.send(new DatagramPacket(responseJson.getBytes(), responseJson.length(), datagramPacket.getSocketAddress()));
                        // 告诉目的主机有链接需要，请目的主机发送数据包

                        PeerResponseMessage assistResponse = new PeerResponseMessage();
                        assistResponse.setDestinationPort(datagramPacket.getPort());
                        assistResponse.setDestinationIP(datagramPacket.getAddress().getHostAddress());
                        assistResponse.setMsg("assistInfo");
                        String assistJson = JSON.toJSONString(assistResponse);
                        serverDataSocket.send(new DatagramPacket(assistJson.getBytes(), assistJson.length(), new InetSocketAddress(peerInfo.getNatIP(), peerInfo.getPort())));
                    }
                } else {
                    // 注册主机信息
                    PeerInfo registerInfo = new PeerInfo();
                    registerInfo.setIntraNetIP(peerMessage.getIntranetIP());
                    registerInfo.setName(peerMessage.getName());
                    registerInfo.setNatIP(datagramPacket.getAddress().getHostName());
                    registerInfo.setPort(datagramPacket.getPort());
                    userList.put(registerInfo.getName(), registerInfo);
                    System.out.println("注册成功：" + registerInfo.getName());
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
            logger.info("创建ServerSocket失败\n");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("接收数据错误\n");
        }
    }
}
