package com.yq.ds.array;

/**
 * @program: JavaDataStructure
 * @description: 数组为下三角矩阵
 * @author: Yuqing
 * @create: 2023-05-30 17:49
 **/
public class LTMArray {

    private int n;
    private int[] array;

    public LTMArray(int[][] nums){
        this.n = nums.length;
        int total = n*(1+n) / 2;
        array = new int[total+1];
        array[total] = nums[0][1];
        int index = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<=i;j++){
                array[index++] = nums[i][j];
            }
        }
    }

    public int get(int i,int j){
        assert i>=0 && i<n && j>=0 && j<n;
        int index;
        if(i >= j){
            index = i*(1+i)/2 + j;
        }else{
            index = n*(n+1) / 2;
        }
        return array[index];
    }
}
