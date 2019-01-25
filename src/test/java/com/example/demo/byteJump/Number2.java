package com.example.demo.byteJump;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Number2 {
    static int maxLength = 0;
    static int currentLength = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nums = in.nextInt();
        in.nextLine();
        while (nums > 0) {
            nums--;
            int nodesNum = in.nextInt();
            in.nextLine();
            List<Integer>[] lists = new List[nodesNum];
            for (int l = 0; l < lists.length; l++) {
                lists[l] = new LinkedList<>();
            }
            for (int i = 0; i < nodesNum; i++) {
                String line = in.nextLine();
                String[] strs = line.split(" ");
                int node1 = Integer.parseInt(strs[0]) - 1;
                int node2 = Integer.parseInt(strs[1]) - 1;
                lists[node1].add(node2);
//                lists[node2].add(node1);
            }
            for (List<Integer> list : lists) {
                find(list, lists, 0);
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                }
            }
            System.out.println(maxLength - 1);
        }
    }
    static int find(List<Integer> list, List<Integer>[] lists, int l) {
        l++;
        if (list.size() > 0) {
            for (Integer index : list) {
                int ll = find(lists[index], lists, l);
                if (ll > currentLength) {
                    currentLength = ll;
                }
            }
        }
        return l;
    }
}

