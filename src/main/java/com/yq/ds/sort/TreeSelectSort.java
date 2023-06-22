package com.yq.ds.sort;

import java.util.Arrays;

/**
 * @program: JavaDataStructure
 * @description: 选择排序 - 树形选择排序
 * @author: Yuqing
 * @create: 2023-06-22 13:12
 **/
public class TreeSelectSort {

    public static void treeSelectSort(int[] nums){
        if(nums==null || nums.length<=1) return;
        int m = nums.length;
        int leafCount = 1;
        // 计算满二叉树的叶子结点数
        while(leafCount < m){
            leafCount *= 2;
        }
        // 假设满二叉树的深度为 h，则叶子结点有 leafCount=2^(h-1)，总结点数是 2^h-1
        // 总结点数 = 2*leafCount - 1
        // tree[0] 不保存结点
        int[] tree = new int[leafCount*2];
        Arrays.fill(tree,Integer.MAX_VALUE);

        // 将数组中的数据保存进满二叉树的叶子结点中
        for(int i=0;i<m;i++){
            tree[tree.length-i-1] = nums[i];
        }

        // 初始化，构建满二叉树
        for(int i=tree.length-1;i>1;i-=2){
            tree[i/2] = Math.min(tree[i],tree[i-1]);
        }

        int index = 0;

        // 将根节点加入 nums
        for(int i=0;i<m;i++){
            nums[index++] = tree[1];
            // 寻找树根值所在的叶子节点的位置
            int minIndex = tree.length - 1;
            while(tree[minIndex] != tree[1]){
                minIndex--;
            }
            tree[minIndex] = Integer.MAX_VALUE;

            // 调整满二叉树
            while (minIndex > 1){
                if(minIndex%2 == 0){
                    tree[minIndex/2] = Math.min(tree[minIndex],tree[minIndex+1]);
                }else{
                    tree[minIndex/2] = Math.min(tree[minIndex],tree[minIndex-1]);
                }
                minIndex /= 2;
            }
        }
    }
}
