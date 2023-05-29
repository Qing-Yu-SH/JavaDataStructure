package com.yq.ds.sort;

import com.yq.ds.util.InputSystem;

/**
 * @program: JavaDataStructure
 * @description: 败者树
 * @author: Yuqing
 * @create: 2023-05-29 18:07
 **/
public class LoserTree {

    private static final int MAX_KEY = Integer.MAX_VALUE;
    private static final int MIN_KEY = Integer.MIN_VALUE;

    private InputSystem inputSystem;

    // 归并段个数
    private int k;

    // 败者树；由于是完全二叉树，所以可以通过一维数组实现
    // [0..k-1] 是完全二叉树的分支
    private int[] ls;

    // 叶子结点
    // [0..k-1] 是完全二叉树的叶子结点；
    // 完整的完全二叉树是 [0..k-1 k..2k-1]，所以可通过 (s+k)/2 求其双亲结点
    private int[] b;


    public LoserTree(int k){
        this.k = k;
        this.ls = new int[k];
        this.b = new int[k+1];
        inputSystem = new InputSystem();
        // 模拟输入过程
        for(int i=0;i<k;i++){
            b[i] = inputSystem.input(i);
        }
        b[k] = MIN_KEY;
    }

    public void createTree(){
        // 设置ls数组中败者的初始值
        for(int i=0;i<k;i++){
            ls[i] = k;
        }

        for(int i=k-1;i>=0;i--){
            adjust(i);
        }

    }

    /**
     * 从叶子结点往上调整败者树
     * @param s 表示胜利者的归并段号
     */
    private void adjust(int s){
        int parent = (s+k) / 2;
        while(parent > 0){
            // 每一个叶子结点同其双亲结点中记录的败者的值相比较，调整败者的值
            if(b[s] > b[ls[parent]]){
                int temp = s;
                s = ls[parent];
                ls[parent] = temp;
            }
            parent /= 2;
        }
        //最终将胜者的值赋给 ls[0]
        ls[0] = s;
    }

    void kMerge(){
        // 创建败者树
        createTree();
        // 最终的胜者存储在 ls[0]中，当其值为 MAX_KEY 时，证明临时文件归并结束
        while(b[ls[0]] != MAX_KEY){
            // 输出过程模拟向外存写的操作
            System.out.format("%d ",b[ls[0]]);
            // 继续读入后续的记录
            b[ls[0]] = inputSystem.input(ls[0]);
            // 根据新读入的记录的关键字的值，重新调整败者树，找出最终的胜者
            adjust(ls[0]);
        }
    }



}
