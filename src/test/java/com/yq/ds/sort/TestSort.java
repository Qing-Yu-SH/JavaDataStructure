package com.yq.ds.sort;

import org.junit.Test;

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

}
