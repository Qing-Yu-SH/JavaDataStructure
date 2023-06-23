package com.yq.ds.sort;

import java.util.Arrays;

/**
 * @program: JavaDataStructure
 * @description: 基数排序：时间复杂度为 O(n+k)，空间复杂度为 O(n+k)，是一种稳定排序；其中 k 表示每个基数可能的取值范围大小
 * @author: Yuqing
 * @create: 2023-06-23 15:51
 **/
public class RadixSort {

    public static void radixSort(int[] nums){
        if(nums==null || nums.length<2) return;
        int n = nums.length;
        int max = Arrays.stream(nums).max().getAsInt();
        // 计算最长数字的长度
        int maxDigitLength = 0;
        while (max != 0){
            maxDigitLength++;
            max /= 10;
        }
        // 使用计数排序算法对基数进行排序；基数范围为 [0,9]
        int[] counting = new int[10];
        int[] result = new int[n];
        // 当前的位数
        int dev = 1;
        // LSD：最低位优先法
        for(int i=0;i<maxDigitLength;i++){
            for(int num: nums){
                int radix = num/dev % 10;
                counting[radix]++;
            }
            // 计算当前基数在结果数组中的开始索引位置
            int preCount = 0;
            for(int j=0;j<counting.length;j++){
                preCount += counting[j];
                counting[j] = preCount - counting[j];
            }
            for(int j=0;j<n;j++){
                int radix = nums[j]/dev % 10;
                result[counting[radix]] = nums[j];
                counting[radix]++;
            }
            // 计数排序完成后，将结果拷贝回 nums 数组
            System.arraycopy(result, 0, nums, 0, n);
            // 将计数数组重置为 0
            Arrays.fill(counting,0);
            dev *= 10;
        }
    }

    public static void radixSortWithNegative(int[] nums){
        if(nums==null || nums.length<2) return;
        // 求绝对值最大值
        int max = Arrays.stream(nums).map(Math::abs).max().getAsInt();
        int maxDigitLength = 0;
        while(max > 0){
            maxDigitLength++;
            max /= 10;
        }
        // 使用计数排序算法对基数进行排序；由于存在负数，所以基数范围为 [-9,9]，为方便存储，将所有基数加 9，变为 [0,18]
        int[] counting = new int[19];
        int[] result = new int[nums.length];
        int dev = 1;
        for(int i=0;i<maxDigitLength;i++){
            for(int value: nums){
                int radix = value/dev % 10;
                counting[radix+9]++;
            }
            // 计算对应 radix 在结果数组中的末尾位置
            for(int j=1;j<counting.length;j++){
                counting[j] += counting[j-1];
            }
            // 为了利用原来 nums 中的顺序，需要倒序遍历，从右到左将 num 放入 result
            for(int j=nums.length-1;j>=0;j--){
                int radix = nums[j]/dev % 10;
                result[--counting[radix+9]] = nums[j];
            }
            // 计数排序完成后，将结果拷贝回 nums 数组
            System.arraycopy(result, 0, nums, 0, nums.length);
            // 将计数数组重置为 0
            Arrays.fill(counting,0);
            dev *= 10;
        }
    }

}
