package com.example.demo.dao;

import com.example.demo.model.RedPacket;
import com.example.demo.model.RedPacketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RedPacketMapper {

    public RedPacket getRedPacket(Integer id);

    public int decreaseRedPacket(Integer id);

    public RedPacket getRedPacketForUpData(Integer id);

    public int decreaseRedPacketForVersion(@Param("id") Integer id, @Param("version") Integer version);

    public int save(RedPacket redPacket);

}