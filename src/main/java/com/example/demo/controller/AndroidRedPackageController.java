package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.configration.netty.ChannelMap;
import com.example.demo.model.ChatMsgRecord;
import com.example.demo.model.RedPacket;
import com.example.demo.service.RedPacketService;
import com.example.demo.utils.ResponseResult;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AndroidRedPackageController {
    @Autowired
    RedPacketService redPacketService;
    @RequestMapping("/sendRedPackage")
    public @ResponseBody ResponseResult sendRedPackage(RedPacket redPacket) {
        int res = redPacketService.save(redPacket);
        ResponseResult responseResult = new ResponseResult();
        if (res < 0 || res == 0) {
            responseResult.setMessage("服务器出错");
        } else {
            ChannelMap.channelMap.entrySet().forEach(entry -> {
                if (entry.getKey().equals(redPacket.getReceiveName())) {
                    ChatMsgRecord chatMsgRecord = new ChatMsgRecord();
                    chatMsgRecord.setType(ChatMsgRecord.TYPE_RED_PACK);
                    entry.getValue().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(chatMsgRecord)));
                    responseResult.setMessage("发送成功");
                }
            });
        }
        return responseResult;
    }
}
