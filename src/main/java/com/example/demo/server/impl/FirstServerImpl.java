package com.example.demo.server.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.RedisIO;
import com.example.demo.model.User;
import com.example.demo.server.FirstServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
//@SuppressWarnings("all")
public class FirstServerImpl implements FirstServer {

    @Resource
    UserMapper userMapper;



    @Cacheable(value="redisCacheManager",key="1")
    public List<User> findAllUser() {
        List<User> list = userMapper.findAll();
        return list;
    }

//    @CacheEvict(value="findAllUser",allEntries=true)//执行此方法的时候删除上面的缓存(以findAllUser为名称的)
//    public int addUser() throws SQLException {
//        // TODO Auto-generated method stub
//        return userMapper.addUser();
//    }
}
