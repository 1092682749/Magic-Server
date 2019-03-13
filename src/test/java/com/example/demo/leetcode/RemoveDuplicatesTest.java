package com.example.demo.leetcode;

public class RemoveDuplicatesTest {
    static int a = 1;
    String name;
    char[] chars = new char[1024];

    void setA(int a) {
        System.out.println(name);
        RemoveDuplicatesTest.a = a;
    }

    int getA() {
        return a;
    }
    RemoveDuplicatesTest(String name)
    {
        this.name = name;
    }
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
    public static void main(String[] args) {
        RemoveDuplicatesTest.a = 2;
        RemoveDuplicatesTest b = new RemoveDuplicatesTest("我是b");
        RemoveDuplicatesTest bb = new RemoveDuplicatesTest("我是bb");
        b.setA(100);
        bb.setA(200);
        System.out.println(b.getA());
        int[] arr = {1, 2, 3, 3, 4};
        new RemoveDuplicatesTest("").removeDuplicates(arr);
        System.out.println(arr);
    }
}
