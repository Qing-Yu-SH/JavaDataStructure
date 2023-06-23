package com.yq.ds.sort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @program: JavaDataStructure
 * @description:
 * @author: Yuqing
 * @create: 2023-05-29 22:45
 **/
public class TestSort {

    long startTime = 0;
    long endTime = 0;

    @Before
    public void start(){
        startTime = System.nanoTime();
    }

    @After
    public void end(){
        endTime = System.nanoTime();
        System.out.printf("执行时长：%.2f 微秒.", (endTime - startTime)/1000.0);
    }

    @Test
    public void test_loserTree(){
        LoserTree loserTree = new LoserTree(5);
        loserTree.kMerge();
    }

    @Test
    public void test_insertSort(){
        int[] nums = new int[]{0,1,5,2,8,6,10,6,12};
        InsertSort.insertSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void test_bInsertSort(){
        int[] nums = new int[]{2,0};
        BinaryInsertSort.bInsertSort(nums);
        System.out.println(Arrays.toString(nums));
        int[] nums2 = new int[]{0,5,6,12,2,22,6,26,10,9,22};
        BinaryInsertSort.bInsertSort(nums2);
        System.out.println(Arrays.toString(nums2));
    }

    @Test
    public void test_shellSort(){
        int[] nums = new int[]{50,36,62,97,82,16,22,50,55,6};
        int[] dt = new int[]{5,3,1};
        ShellInsertSort.shellSort(nums,dt);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void test_bubbleSort(){
        int[] nums = new int[]{50,36,62,97,82,16,22,50,55,6};
        BubbleSort.bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void test_quickSort(){
        int[] nums = new int[]{50,36,62,97,82,16,22,50,55,6};
        QuickSort.quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void test_selectSort(){
        int[] nums = new int[]{50,36,62,97,82,16,22,50,55,6};
        SelectSort.selectSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void test_treeSelectSort(){
        int[] nums = new int[]{50,36,62,97,82,16,22,50,55,6};
        TreeSelectSort.treeSelectSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void test_heapSort(){
        int[] nums = new int[]{50,36,62,97,82,16,22,50,55,6};
        HeapSort.heapSort(nums,HeapSort.CODE_MAX_HEAP);
        System.out.println(Arrays.toString(nums));
        int[] nums2 = new int[]{50,36,62,97,82,16,22,50,55,6};
        HeapSort.heapSort(nums2,HeapSort.CODE_MIN_HEAP);
        System.out.println(Arrays.toString(nums2));
    }

    @Test
    public void test_mergeSort(){
        int[] nums = new int[]{50,36,62,97,82,16,22,50,55,6};
        MergeSort.mergeSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void test_countingSort(){
        int[] nums = new int[]{50,36,62,97,82,16,22,50,55,6};
        CountingSort.countingSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void test_radixSort(){
        int[] nums = new int[]{68,62,20,2,86,15,51,9,92,36};
        RadixSort.radixSort(nums);
        System.out.println(Arrays.toString(nums));
        int[] nums2 = new int[]{-1,68,62,20,2,86,-102,15,51,-2,9,92,36,-8,-6};
        RadixSort.radixSortWithNegative(nums2);
        System.out.println(Arrays.toString(nums2));
    }

    @Test
    public void test_bucketSort(){
        int[] nums = new int[]{50,36,62,97,82,16,22,50,55,6};
        BucketSort.bucketSort_Array(nums);
        long end = System.nanoTime();
        System.out.println(Arrays.toString(nums));
        System.out.printf("以数组为桶的桶排序的执行时长：%.2f 微秒.\n", (end - startTime)/1000.0);
        int[] nums2 = new int[]{50,36,62,97,82,16,22,50,55,6};
        BucketSort.bucketSort_Linked(nums2);
        long end2 = System.nanoTime();
        System.out.println(Arrays.toString(nums2));
        System.out.printf("以链表为桶的桶排序的执行时长：%.2f 微秒.\n", (end2 - end)/1000.0);
        int[] nums3 = new int[]{50,36,62,97,82,16,22,50,55,6};
        BucketSort.bucketSort_Compromise(nums3);
        long end3 = System.nanoTime();
        System.out.println(Arrays.toString(nums3));
        System.out.printf("采用折中的桶排序的执行时长：%.2f 微秒.\n", (end3 - end2)/1000.0);
    }

}
