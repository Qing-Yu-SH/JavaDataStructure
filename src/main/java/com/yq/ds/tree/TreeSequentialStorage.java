package com.yq.ds.tree;

/**
 * @program: JavaDataStructure
 * @description: 二叉树顺序存储
 * @author: Yuqing
 * @create: 2023-06-05 14:01
 **/
public class TreeSequentialStorage {

    private int[] nodes;
    private int height;
    private int nodeNum;

    public TreeSequentialStorage(TreeNode root) {
        this.height = getHeight(root);
        this.nodeNum = (int)Math.pow(2,height) - 1;
        nodes = new int[nodeNum+1];
        convert2Array(root,1);
    }

    private int getHeight(TreeNode root){
        if(root == null) return 0;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        return Math.max(leftHeight,rightHeight) + 1;
    }

    /**
     * 将链式存储的二叉树转换为顺序存储
     */
    private void convert2Array(TreeNode root,int id){
        if(root == null) return;
        nodes[id] = root.val;
        convert2Array(root.left,2*id);
        convert2Array(root.right,2*id+1);
    }

    public int[] getNodes() {
        return nodes;
    }

}
