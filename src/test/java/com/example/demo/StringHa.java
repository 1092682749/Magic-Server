package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class StringHa {
    static class Name {
        String username;
        Name(String username) {
            this.username = username;
        }

        @Override
        public int hashCode() {
            return username.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Name)) return false;
            Name o = (Name) obj;
            return o.username.equals(username);
        }
    }
    public static void main(String[] args) {
        Name name1 = new Name("1");
        Name name2 = new Name("1");
        Map<Name, String> map = new HashMap<>();
        map.put(name1, "1");
        System.out.println(map.containsKey(name2));
        Map<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put(new String("aaa"), "aaaa");
        Boolean b = stringStringMap.containsKey(new String("aaa"));
        System.out.println(b);
    }
}

