package com.yq.ds.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @program: JavaDataStructure
 * @description:
 * @author: Yuqing
 * @create: 2023-05-29 22:45
 **/
public class TestSort {

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

}
