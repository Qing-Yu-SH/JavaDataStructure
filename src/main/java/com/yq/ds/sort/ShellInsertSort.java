package com.yq.ds.sort;

/**
 * @program: JavaDataStructure
 * @description: 希尔排序
 * @author: Yuqing
 * @create: 2023-06-22 12:39
 **/
public class ShellInsertSort {

    /**
     * 希尔排序
     * @param nums 数组
     * @param dt 步长数组
     */
    public static void shellSort(int[] nums,int[] dt){
        for(int i=0;i<dt.length;i++){
            shellSort(nums,dt[i]);
        }
    }

    private static void shellSort(int[] nums,int dk){
        for(int i=dk;i<nums.length;i++){
            if(nums[i] < nums[i-dk]){
                int pivot = nums[i];
                int j = i - dk;
                for(;j>=0 && nums[j]>pivot;j-=dk){
                    nums[j+dk] = nums[j];
                }
                nums[j+dk] = pivot;
            }
        }
    }
}
