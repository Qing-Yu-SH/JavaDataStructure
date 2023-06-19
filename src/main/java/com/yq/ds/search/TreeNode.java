package com.yq.ds.search;

/**
 * @program: JavaDataStructure
 * @description: 树节点
 * @author: Yuqing
 * @create: 2023-06-19 20:36
 **/
public class TreeNode<E extends Comparable> {
    E val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(E val) { this.val = val; }
    TreeNode(E val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
