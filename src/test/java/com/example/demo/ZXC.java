package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.server.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
public class ZXC {
    @Autowired
    UserService userService;
        @Transactional
        public void a() throws Exception {
            String s = "adsasdasd";
            char[] chars = s.toCharArray();
            Set<Character> characterSet = new HashSet<>();
            characterSet.add(chars[0]);
//            throw new Exception();
            User user = new User();
            user.setNickName("a");
            user.setUsername("aaaaaaa");
            try {
                userService.save(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Test
    public void test() {
            String s1 = " ";
           int len = lengthOfLongestSubstring(s1);
           System.out.println(len);
        }
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int length = 0;
        Set<Character> set = new HashSet<>();
        for(int i = 0; i < chars.length; i++) {
            for(int j = i; j < chars.length; j++) {
                int begin = set.size();
                set.add(chars[j]);
                int end = set.size();
                if (begin == end) {
                    if(end > length) length = end;
                    set.clear();
                    break;
                }
            }
        }
        return length;
    }

    public static void main(String[] args) {
            int sum = 0, i = 0;
           for (;;) {
               if (i > 10)
                   break;
               sum += i++;
           }
           System.out.println(sum);
    }
}
