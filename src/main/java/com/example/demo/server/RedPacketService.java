package com.example.demo.server;

import com.example.demo.model.RedPacket;

public interface RedPacketService {

    public RedPacket getRedPacket(Integer id);

    public int decreaseRedPacket(Integer id);
}
