package com.yq.ds.sort;

import java.util.Arrays;

/**
 * @program: JavaDataStructure
 * @description: 计数排序：计数排序就是一种时间复杂度为 O(n) 的稳定排序算法
 * @author: Yuqing
 * @create: 2023-06-23 14:52
 **/
public class CountingSort {

    public static void countingSort(int[] nums){
        if(nums==null || nums.length<2) return;
        // 找到最大值和最小值
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();
        // 确定计数范围
        int range = max - min + 1;
        // 建立计数数组，下标 0~range 对应 min~max
        int[] counting = new int[range];
        // 计数
        for(int num: nums){
            // 将每个整数出现的次数统计到计数数组中对应下标的位置，这里需要将每个元素减去 min，才能映射到 0～range-1 范围内
            counting[num-min]++;
        }
        // 记录前面比自己小的数字的总数
        int preCounts = 0;
        for(int i=0;i<range;i++){
            preCounts += counting[i];
            // counting[i]：代表元素 i+1 在排序后数组中的索引位置
            counting[i] = preCounts - counting[i];
        }
        int[] result = new int[nums.length];
        for(int num: nums){
            // counting[num - min]：表示此元素在排序数组中的下标
            result[counting[num-min]] = num;
            // 更新 counting[num - min]：指向此元素的下一个下标
            counting[num-min]++;
        }
        // 将结果赋值回 nums
        System.arraycopy(result, 0, nums, 0, nums.length);
    }
}
