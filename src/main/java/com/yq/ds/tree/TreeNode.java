package com.yq.ds.tree;

/**
 * @program: JavaDataStructure
 * @description: 二叉树结点；链式存储结构
 * @author: Yuqing
 * @create: 2023-05-30 21:01
 **/
public class TreeNode<E> {

    E val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(E val) {
        this.val = val;
    }

    public TreeNode(E val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
