package com.yq.ds.str;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @program: JavaDataStructure
 * @description:
 * @author: Yuqing
 * @create: 2023-05-29 16:57
 **/
public class TestPatternStr {

    long startTime = 0;
    long endTime = 0;

    @Before
    public void start(){
        // 返回的是纳秒（ns）
        startTime = System.nanoTime();
    }

    @After
    public void after(){
        endTime = System.nanoTime();
        double cost = (double) (endTime - startTime) / 1e6;
        // 1s = 1000 ms = 1e6 us = 1e9 ns
        System.out.println("cost: " + cost + "ms");
    }

    @Test
    public void test_BF(){
        String S = "ababaababaabcfaamof";
        String T = "abaabcf";
        String T2 = "abaac";
        BruteForce bf = new BruteForce();
        int index = bf.index_BF(S, T);
        System.out.println("位置：" + index);
        int index2 = bf.index_BF(S, T2);
        System.out.println("位置：" + index2);
    }

    @Test
    public void test_KMP(){
        String S = "ababaababaabcfaamof";
        String T = "abaabcf";
        String T2 = "abaac";
        KMP kmp = new KMP();
        int index = kmp.index_KMP(S, T);
        System.out.println("位置：" + index);
        int index2 = kmp.index_KMP(S, T2);
        System.out.println("位置：" + index2);
    }
}
