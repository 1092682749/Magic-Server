package com.example.demo.service.impl;

import com.example.demo.dao.RedPacketMapper;
import com.example.demo.dao.UserRedPacketMapper;
import com.example.demo.model.RedPacket;
import com.example.demo.model.UserRedPacket;
import com.example.demo.service.RedisRedPacketService;
import com.example.demo.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import javax.annotation.Resource;

@Service
public class UserRedPacketServiceImpl implements UserRedPacketService {
    @Resource
    private UserRedPacketMapper userRedPacketMapper;

    @Resource
    private RedPacketMapper redPacketMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisRedPacketService redisRedPacketService;

    @Autowired
    private Jedis jedis;

    private static final int FAILED = 0;

    //悲观锁实现并发安全
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int grapRedPacket(Integer redPacketId, Integer userId) {
        RedPacket redPacket = redPacketMapper.getRedPacketForUpData(redPacketId);
        if (redPacket.getStock() > 0){
            redPacketMapper.decreaseRedPacket(redPacketId);
            UserRedPacket userRedPacket = new UserRedPacket();
            userRedPacket.setRedPacketId(redPacketId);
            userRedPacket.setUserId(userId);
            userRedPacket.setAmount(redPacket.getAmount());
            userRedPacket.setNote("抢红包"+redPacketId);
            int result = userRedPacketMapper.grapRedPacket(userRedPacket);
            return result;
        }
        return FAILED;
    }

    // Lua脚本
    String script = "local listKey = 'red_packet_list_'..KEYS[1] \n"
            + "local redPacket = 'red_packet_'..KEYS[1] \n"
            + "local stock = tonumber(redis.call('hget', redPacket, 'stock')) \n"
            + "if stock <= 0 then return 0 end \n"
            + "stock = stock -1 \n"
            + "redis.call('hset', redPacket, 'stock', tostring(stock)) \n"
            + "redis.call('rpush', listKey, ARGV[1]) \n"
            + "if stock == 0 then return 2 end \n"
            + "return 1 \n";

    String sha1 = null;

    @Override
    public Long grapRedPacketByRedis(Integer redPacketId, Integer userId) {

        String args = userId + "-" + System.currentTimeMillis();
        Long result = null;

//         Jedis jedis = (Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        try {
            if (sha1 == null) {
                sha1 = jedis.scriptLoad(script);
            }
            Object res = jedis.evalsha(sha1, 1, redPacketId + "", args);
            result = (Long) res;
            if (result == 2) {
                String unitAmountStr = jedis.hget("red_packet_" + redPacketId, "unit_amount");
                Double unitAmount = Double.parseDouble(unitAmountStr);
                System.err.println("thread_name = " + Thread.currentThread().getName());
                redisRedPacketService.saveUserRedPacketByRedis(redPacketId, unitAmount);
            }
        } finally {

            if (jedis != null && jedis.isConnected()) {
                jedis.close();
            }
        }
        return result;
    }

    //乐观锁方式实现并发安全
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int grapRedPacketForVersion(Integer redPacketId, Integer userId) {
        //锁重入机制，最多尝试十次
        for (int i = 0; i  < 10; i++){
            RedPacket redPacket = redPacketMapper.getRedPacket(redPacketId);
            //判断是否还有库存
            if (redPacket.getStock() > 0) {

                //使用版本号决定是否更新数据
                int update = redPacketMapper.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());

                if (update == 0) {
                    return FAILED;
                }
                //生成红包信息用于保存数据库
                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setUserId(userId);
                userRedPacket.setAmount(redPacket.getUnitAmount());
                userRedPacket.setNote("红包id " + redPacketId);
                int result = userRedPacketMapper.grapRedPacket(userRedPacket);
                return result;
            }
        }
        return FAILED;
    }
}
