package com.yq.ds.sort;

/**
 * @program: JavaDataStructure
 * @description: 选择排序 - 简单选择排序
 * @author: Yuqing
 * @create: 2023-06-22 13:05
 **/
public class SelectSort {

    public static void selectSort(int[] nums){
        if(nums==null || nums.length<=1) return;;
        for(int i=0;i<nums.length;i++){
            int loc = i;
            // 往后寻找最小的值
            for(int j=i+1;j<nums.length;j++){
                if(nums[loc] > nums[j]){
                    loc = j;
                }
            }
            if(loc != i){
                int t = nums[i];
                nums[i] = nums[loc];
                nums[loc] = t;
            }
        }
    }
}
