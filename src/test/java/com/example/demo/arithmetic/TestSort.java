package com.example.demo.arithmetic;

import java.util.Random;

public class TestSort {

    public static void main(String[] args) throws Exception {
        int[] array = new int[]{20,9,8,7,6,4,1,2,3,5,10,11,18};
        quickSort(array,0,array.length-1);
        System.out.println(array);
    }

    public static void quickSort(int[] array,int start,int end){
        if(start>=end) return;
        //获取随机分隔元素
        int randomIndex = new Random().nextInt(end-start)+start;
        int random = array[randomIndex];
        //随机元素放到数组头部
        swap(array,randomIndex,start);
        int mark = start;
        //循环处理数组
        for(int i=start+1;i<=end;i++){
            if(array[i]<=random){
                mark++;
                swap(array,i,mark);
            }
        }
        swap(array,start,mark);
        //递归两个子数组
        quickSort(array,start,mark-1);
        quickSort(array,mark+1,end);
    }

    //交换位置函数
    public static void swap(int array[],int a,int b){
        if(a==b) return;
        array[a] = array[a]^array[b];
        array[b] = array[a]^array[b];
        array[a] = array[a]^array[b];
    }
}
