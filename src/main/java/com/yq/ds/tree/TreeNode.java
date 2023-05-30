package com.yq.ds.tree;

/**
 * @program: JavaDataStructure
 * @description: 二叉树结点；链式存储结构
 * @author: Yuqing
 * @create: 2023-05-30 21:01
 **/
public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
