package com.example.demo.collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListIterator {
    public static void main(String[] args) {
        List<String> ss = new LinkedList<>();
        ss.add("ss");
        Iterator iterator = ss.iterator();
        System.out.println(iterator.next());
        List<String> sss = new LinkedList<>();
        sss.add("sss");
        System.out.println(sss.iterator().next());
    }
}
