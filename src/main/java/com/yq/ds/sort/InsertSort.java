package com.yq.ds.sort;

/**
 * @program: JavaDataStructure
 * @description: 直接插入排序
 * @author: Yuqing
 * @create: 2023-06-07 23:04
 **/
public class InsertSort {

    public static void insertSort(int[] nums){
        // 数组下标从 1 开始
        if(nums==null || nums.length==2) return;
        for(int i=2;i<nums.length;i++){
            if(nums[i-1] > nums[i]){
                // 在 nums[0] 处加入哨兵
                nums[0] = nums[i];
                int index = i-1;
                // 从后向前查找插入位置
                int j = i-1;
                for(;nums[0] < nums[j];j--){
                    nums[j+1] = nums[j];
                }
                nums[j+1] = nums[0];
            }
        }
        nums[0] = 0;
    }

}
