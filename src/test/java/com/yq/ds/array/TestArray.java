package com.yq.ds.array;

import org.junit.Test;

/**
 * @program: JavaDataStructure
 * @description:
 * @author: Yuqing
 * @create: 2023-05-30 17:28
 **/
public class TestArray {

    @Test
    public void test_UTMArray(){
        int[][] array = new int[][]{
                {1,2,3,4,5},
                {0,6,7,8,9},
                {0,0,10,11,12},
                {0,0,0,13,14},
                {0,0,0,0,15}
        };
        UTMArray utmArray = new UTMArray(array);
        System.out.println(array[0][0] + "\t : \t" + utmArray.get(0,0));
        System.out.println(array[2][1] + "\t : \t" + utmArray.get(2,1));
        System.out.println(array[2][2] + "\t : \t" + utmArray.get(2,2));
        System.out.println(array[1][2] + "\t : \t" + utmArray.get(1,2));
        System.out.println(array[3][4] + "\t : \t" + utmArray.get(3,4));
        System.out.println(array[3][2] + "\t : \t" + utmArray.get(3,2));
    }

    @Test
    public void test_LTMArray(){
        int[][] array = new int[][]{
                {1,0,0,0,0,0},
                {2,3,0,0,0,0},
                {3,4,5,0,0,0},
                {6,7,8,9,0,0},
                {9,8,7,6,5,0},
                {1,2,3,4,5,6},
        };
        LTMArray ltmArray = new LTMArray(array);
        System.out.println(array[0][0] + "\t : \t" + ltmArray.get(0,0));
        System.out.println(array[1][1] + "\t : \t" + ltmArray.get(1,1));
        System.out.println(array[2][2] + "\t : \t" + ltmArray.get(2,2));
        System.out.println(array[1][2] + "\t : \t" + ltmArray.get(1,2));
        System.out.println(array[5][2] + "\t : \t" + ltmArray.get(5,2));
        System.out.println(array[5][0] + "\t : \t" + ltmArray.get(5,0));
    }

}
