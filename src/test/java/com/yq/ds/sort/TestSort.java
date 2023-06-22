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

}
