package com.example.demo;

import com.example.demo.model.CmpPOJO;

import java.util.LinkedList;
import java.util.List;

public class TestCmpPojo {
    public static void main(String[] args){
        CmpPOJO c1 = new CmpPOJO();
        c1.setId(1);
        System.out.println(c1.hashCode());
        CmpPOJO c2 = new CmpPOJO();
        c2.setId(1);
        System.out.println(c2.hashCode());
        System.out.println(c1.equals(c2));
        List<CmpPOJO> l1 = new LinkedList<>();
        l1.add(c1);
        List<CmpPOJO> l2 = new LinkedList<>();
        l2.add(c2);
        System.out.println(l1);
    }
}
