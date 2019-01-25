package com.example.demo.byteJump;


import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Number1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nums = in.nextInt();
        in.nextLine();
        for (int i = 0; i < nums; i++) {
            String line = in.nextLine();
            String[] strs = line.split(" ");
            List<Integer> integerList = new LinkedList<>();
            for (String s : strs) {
                integerList.add(Integer.parseInt(s));
            }
            List<Integer> handerSort = new LinkedList<>();
            for (Integer element : integerList) {
                handerSort.add(element);
                if (integerList.indexOf(element) != integerList.size() - 1) {
                    Integer firstElement = handerSort.remove(0);
                    handerSort.add(firstElement);
                }
            }
            for (int j = handerSort.size() - 1; j >= 0 ; j--) {
                System.out.print(handerSort.get(j) + " ");
            }
            System.out.println();
        }
    }
}
