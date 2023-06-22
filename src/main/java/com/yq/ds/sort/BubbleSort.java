package com.yq.ds.sort;

/**
 * @program: JavaDataStructure
 * @description: 交换排序 - 冒泡排序
 * @author: Yuqing
 * @create: 2023-06-22 12:48
 **/
public class BubbleSort {

    public static void bubbleSort(int[] nums){
        if(nums==null || nums.length<=1) return;
        int m = nums.length;
        // 标记是否在排序过程中存在交换操作
        boolean flag = true;
        while (m>1 && flag){
            flag = false;
            for(int j=1;j<m;j++){
                if(nums[j-1] > nums[j]){
                    int t = nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] = t;
                    flag = true;
                }
            }
            m--;
        }
    }
}
