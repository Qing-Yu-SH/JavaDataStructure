package com.yq.ds.sort;

/**
 * @program: JavaDataStructure
 * @description: 折半插入排序
 * @author: Yuqing
 * @create: 2023-06-22 12:28
 **/
public class BinaryInsertSort {

    public static void bInsertSort(int[] nums){
        if(nums==null || nums.length<=1) return;
        for(int i=1;i<nums.length;i++){
            int pivot = nums[i];
            int left = 0,right = i-1;
            // 寻找右侧边界的二分查找
            while (left <= right){
                int mid = (right-left)/2 + left;
                if(nums[mid] <= pivot){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
            for(int j=i-1;j>=left;j--){
                nums[j+1] = nums[j];
            }
            nums[left] = pivot;
        }
    }
}
