package com.example.demo.dao;

import com.example.demo.model.UserRedPacket;
import com.example.demo.model.UserRedPacketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRedPacketMapper {
    public int grapRedPacket(UserRedPacket userRedPacket);
}