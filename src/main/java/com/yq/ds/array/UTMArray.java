package com.yq.ds.array;

/**
 * @program: JavaDataStructure
 * @description: 数组为上三角矩阵存储方式；对称矩阵是 n 阶方阵；
 * @author: Yuqing
 * @create: 2023-05-30 17:10
 **/
public class UTMArray {

    private int[] array;
    private int n;
    private int total;

    public UTMArray(int[][] nums){
        this.n = nums.length;
        this.total = n*(1+n) / 2;
        this.array = new int[total+1];
        array[total] = nums[n-1][0];
        int index = 0;
        // 从最后一行开始逐行存放
        for(int i=n-1;i>=0;i--){
            for(int j=i;j<n;j++){
                array[index++] = nums[i][j];
            }
        }
    }

    public int get(int i,int j){
        assert i>=0 && i<n && j>=0 && j<=n;
        int index;
        if(i <= j){
            index = (n-i-1)*(1+n-i-1)/2 + j - i;
        }else{
            index = n*(n+1)/2;
        }
        return array[index];
    }
}
