package com.example.demo.service.impl;

import com.example.demo.dao.FriendMapper;
import com.example.demo.model.Friend;
import com.example.demo.model.User;
import com.example.demo.service.FriendService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Resource
    FriendMapper friendMapper;
    @Resource
    UserService userService;
    @Override
    public void insert(Friend friend) {
        friendMapper.insert(friend);
    }

    @Override
    @Transactional
    public int addFriendRelate(Integer id1, Integer id2) {
        friendMapper.addFriendRelate(id1, id2);
        int res = friendMapper.addFriendRelate(id2, id1);
        return res;
    }

    @Override
    public List<User> findMyFriend() throws Exception {
        List<User> friendList = new LinkedList<>();
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(details.getUsername());
        List<Friend> friends = friendMapper.findMyFriend(user.getId());
        for (Friend friend : friends) {
            User friendUser = userService.findById(friend.getYouId());
            if (friendUser != null) {
                friendList.add(friendUser);
            }
        }
        return friendList;
    }

    @Override
    public List<User> findMyAllFriend() throws Exception {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(details.getUsername());
        List<User> userList = friendMapper.findMyAllFriend(user.getId());
        return userList;
    }

    @Override
    public List<User> findMyAllFriend(User user) {
        List<User> userList = friendMapper.findMyAllFriend(user.getId());
        return userList;
    }
}
