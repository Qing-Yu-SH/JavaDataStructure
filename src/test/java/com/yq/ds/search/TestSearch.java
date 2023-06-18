package com.yq.ds.search;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @program: JavaDataStructure
 * @description:
 * @author: Yuqing
 * @create: 2023-06-18 18:35
 **/
public class TestSearch {

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
    public void test_orderSearchWithSentinel(){
        // 位置 0 不放元素
        Integer[] elements = new Integer[]{0,5,2,6,32,8,9,12,22,52,62,72,86,96,100,102,106,112,200,105,108};
        OrderSearch orderSearch = new OrderSearch();
        int pos = orderSearch.orderSearch(elements, 2);
        System.out.println("元素 2 的位置：" + pos);
        int pos2 = orderSearch.orderSearch(elements, 220);
        System.out.println("元素 220 的位置：" + pos2);
    }

    @Test
    public void test_orderSearchWithoutSentinel(){
        // 位置 0 不放元素
        Integer[] elements = new Integer[]{0,5,2,6,32,8,9,12,22,52,62,72,86,96,100,102,106,112,200,105,108};
        OrderSearch orderSearch = new OrderSearch();
        int pos = orderSearch.orderSearchWithoutSentinel(elements, 2);
        System.out.println("元素 2 的位置：" + pos);
        int pos2 = orderSearch.orderSearchWithoutSentinel(elements, 220);
        System.out.println("元素 220 的位置：" + pos2);
    }
}
