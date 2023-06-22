package com.yq.ds.sort;

/**
 * @program: JavaDataStructure
 * @description: 交换排序 - 快速排序
 * @author: Yuqing
 * @create: 2023-06-22 12:55
 **/
public class QuickSort {

    public static void quickSort(int[] nums){
        if(nums==null || nums.length<=1) return;
        quickSort(nums,0,nums.length-1);
    }

    private static void quickSort(int[] nums,int low,int high){
        if(low < high){
            int pivotLoc = partition(nums,low,high);
            quickSort(nums,low,pivotLoc-1);
            quickSort(nums,pivotLoc+1,high);
        }
    }

    private static int partition(int[] nums,int low,int high){
        int pivot = nums[low];
        while (low < high){
            while (low<high && nums[high]>=pivot) high--;
            nums[low] = nums[high];
            while (low<high && nums[low]<=pivot) low++;
            nums[high] = nums[low];
        }
        nums[low] = pivot;
        return low;
    }
}
