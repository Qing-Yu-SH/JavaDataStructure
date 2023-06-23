package com.yq.ds.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @program: JavaDataStructure
 * @description: 桶排序：时间复杂度 O(n)，空间复杂度 O(n)
 *               思想：将区间划分为 n 个相同大小的子区间，每个子区间称为桶；遍历数组，将每个数字装入桶中；对每个桶内的数字单独排序；最后按照顺序将所有桶内的数字合并起来
 *               桶排序在实际工作中的应用较少，不仅因为它需要借助于其他排序算法，还因为桶排序算法基于一个假设：所有输入数据都服从均匀分布，也就是说输入数据应该尽可能地均匀分布在每个桶中。只有这个假设成立时，桶排序运行效率才比较高
 * @author: Yuqing
 * @create: 2023-06-23 17:39
 **/
public class BucketSort {

    /**
     * 以数组为桶的桶排序
     * @param nums 待排序数组
     */
    public static void bucketSort_Array(int[] nums){
        if(nums==null || nums.length<2) return;
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();
        // 确定取值范围
        int range = max - min;
        // 设置桶的数量，这里我们设置为 100 个，可以根据实际情况修改。
        int bucketAmount = 100;
        // 桶和桶之间的间距；如果恰好根据 bucketAmount 分间隔，可能存在进位问题
        double gap = range*1.0 / (bucketAmount-1);
        // 用二维数组来装桶，第一个维度是桶的编号，第二个维度是桶中的数字。初始化长度为 0
        int[][] buckets = new int[bucketAmount][];
        // 装桶
        for (int value : nums) {
            // 找到 value 的桶号
            int index = (int) ((value - min) / gap);
            buckets[index] = add(buckets[index], value);
        }
        int index = 0;
        // 对每个桶内的数字进行单独排序
        for(int i=0;i<bucketAmount;i++){
            if (buckets[i] == null || buckets[i].length == 0) continue;
            // 这里需要用到别的排序算法对桶内数据进行排序
            insertSort(buckets[i]);
            // 排序完成后将桶内的结果收集起来
            System.arraycopy(buckets[i], 0, nums, index, buckets[i].length);
            index += buckets[i].length;
        }
    }

    /**
     * 数组扩容
     * @param arr 数组
     * @param num 要加入的元素
     * @return 扩容并加入元素后的新数组
     */
    private static int[] add(int[] arr,int num){
        if(arr == null) return new int[]{num};
        int[] newArr = Arrays.copyOf(arr,arr.length+1);
        newArr[arr.length] = num;
        return newArr;
    }

    private static void insertSort(int[] nums){
        for(int i=1;i<nums.length;i++){
            int cur = nums[i];
            int j = i-1;
            while(j>=0 && cur<nums[j]){
                nums[j+1] = nums[j];
                j--;
            }
            nums[j+1] = cur;
        }
    }

    /**
     * 以链表为桶的桶排序
     * @param nums 要排序的数组
     */
    public static void bucketSort_Linked(int[] nums){
        if(nums==null || nums.length<2) return;
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();
        // 确定取值范围
        int range = max - min;
        // 设置桶的数量，这里我们设置为 100 个，可以根据实际情况修改。
        int bucketAmount = 100;
        // 桶和桶之间的间距；如果恰好根据 bucketAmount 分间隔，可能存在进位问题
        double gap = range*1.0 / (bucketAmount-1);
        HashMap<Integer, LinkedList<Integer>> buckets = new HashMap<>();
        // 装桶
        for (int value : nums) {
            // 找到 value 的桶号
            int index = (int) ((value - min) / gap);
            if (!buckets.containsKey(index)) {
                buckets.put(index, new LinkedList<>());
            }
            buckets.get(index).add(value);
        }

        int index = 0;
        // 对每个桶内的数字进行单独排序
        for (int i = 0; i < bucketAmount; i++) {
            LinkedList<Integer> bucket = buckets.get(i);
            if (bucket == null) continue;
            // 这里需要结合其他排序算法，例如：插入排序
            insertSort(bucket);
            // 排序完成后将桶内的结果收集起来
            for (int num : bucket) {
                nums[index++] = num;
            }
        }

    }

    private static void insertSort(LinkedList<Integer> nums){
        for(int i=1;i<nums.size();i++){
            int cur = nums.get(i);
            int j = i-1;
            while(j>=0 && cur<nums.get(j)){
                nums.set(j+1,nums.get(j));
                j--;
            }
            nums.set(j+1,cur);
        }
    }

    /**
     * 折中的方案：装桶时用链表，桶内排序用数组
     * 装桶时使用链表，避免扩容问题；排序时将链表转换为数组，避免链表在定位索引时需要从头遍历的问题，以及装箱和拆箱的性能消耗
     * @param nums
     */
    public static void bucketSort_Compromise(int[] nums){
        if(nums==null || nums.length<2) return;
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();
        // 确定取值范围
        int range = max - min;
        // 设置桶的数量，这里我们设置为 100 个，可以根据实际情况修改。
        int bucketAmount = 100;
        // 桶和桶之间的间距；如果恰好根据 bucketAmount 分间隔，可能存在进位问题
        double gap = range*1.0 / (bucketAmount-1);
        HashMap<Integer, LinkedList<Integer>> buckets = new HashMap<>();
        // 装桶
        for (int value : nums) {
            // 找到 value 的桶号
            int index = (int) ((value - min) / gap);
            if (!buckets.containsKey(index)) {
                buckets.put(index, new LinkedList<>());
            }
            buckets.get(index).add(value);
        }

        int index = 0;
        // 对每个桶内的数字进行单独排序
        for(int i=0;i<bucketAmount;i++){
            LinkedList<Integer> bucket = buckets.get(i);
            if (bucket == null) continue;
            // 将链表转换为数组，提升排序性能
            int[] arrBucket = bucket.stream().mapToInt(Integer::intValue).toArray();
            // 这里需要用到别的排序算法对桶内数据进行排序
            insertSort(arrBucket);
            // 排序完成后将桶内的结果收集起来
            System.arraycopy(arrBucket, 0, nums, index,arrBucket.length);
            index += arrBucket.length;
        }
    }
}
