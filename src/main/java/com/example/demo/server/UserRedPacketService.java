package com.example.demo.server;

import com.example.demo.model.UserRedPacket;

public interface UserRedPacketService {
    public int grapRedPacket(Integer redPacketId,Integer userId);
    public int grapRedPacketForVersion(Integer redPacketId, Integer userId);
    public Long grapRedPacketByRedis(Integer redPacketId, Integer userId);
}
