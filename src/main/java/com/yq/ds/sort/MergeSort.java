package com.yq.ds.sort;

/**
 * @program: JavaDataStructure
 * @description: 归并排序
 * @author: Yuqing
 * @create: 2023-06-22 13:59
 **/
public class MergeSort {

    public static void mergeSort(int[] nums){
        if(nums==null || nums.length<=1) return;
        int[] T = new int[nums.length];
        MSort(nums,T,0,nums.length-1);
    }

    private static void MSort(int[] nums,int[] T,int low,int high){
        if(low != high){
            int mid = (high-low)/2 + low;
            MSort(nums, T, low, mid);
            MSort(nums, T, mid+1, high);
            Merge(nums,T,low,mid,high);
        }
    }

    private static void Merge(int[] nums,int[] T,int low,int mid,int high){
        int i = low,j = mid+1,k = low;
        while (i<=mid && j<=high){
            if(nums[i] <= nums[j]){
                T[k++] = nums[i];
                i++;
            }else{
                T[k++] = nums[j];
                j++;
            }
        }
        while(i <= mid){
            T[k++] = nums[i++];
        }
        while(j <= high){
            T[k++] = nums[j++];
        }
        // 将合并后的有序表更新到 nums 数组
        if(high-low+1 > 0){
            System.arraycopy(T,low,nums,low,high-low+1);
        }
    }
}
