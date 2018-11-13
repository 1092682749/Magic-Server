package com.example.demo.controller;

import com.example.demo.server.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/ok/userRedPacket")
public class UserRedPacketController {

    @Autowired
    private UserRedPacketService userRedPacketService;

    @RequestMapping("/grapRedPacket")
    public @ResponseBody Map<String,Object> grapRedPacket(Integer redPacketId,Integer userId){
        int result = userRedPacketService.grapRedPacket(redPacketId,userId);
        Map<String,Object> resultMap = new HashMap<String, Object>();
        boolean flag = result > 0;
        resultMap.put("isSuccess",flag);
        resultMap.put("message",flag?"抢红包成功":"抢红包失败");
        System.out.println(resultMap.get("message"));
        return resultMap;
    }

    @RequestMapping(value = "/grapRedPacketByRedis")
    @ResponseBody
    public Map<String, Object> grapRedPacketByRedis(Integer redPacketId, Integer userId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Long result = userRedPacketService.grapRedPacketByRedis(redPacketId, userId);
        boolean flag = result > 0;
        resultMap.put("result", flag);
        resultMap.put("message",flag?"抢红包成功":"抢红包失败");
        System.out.println(resultMap.get("message"));
        return resultMap;
    }

    @RequestMapping(value = "/grapRedPacketForVersion")
    @ResponseBody
    public Map<String, Object> grapRedPacketForVersion(Integer redPacketId, Integer userId) {

        int result = userRedPacketService.grapRedPacketForVersion(redPacketId, userId);
        Map<String, Object> retMap = new HashMap<String, Object>();
        boolean flag = result > 0;
        retMap.put("success", flag);
        retMap.put("message",flag?"抢红包成功":"抢红包失败");
        return retMap;
    }
}
