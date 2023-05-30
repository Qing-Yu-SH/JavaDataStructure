package com.yq.ds.tree;

import java.util.Stack;

/**
 * @program: JavaDataStructure
 * @description: 前序遍历
 * @author: Yuqing
 * @create: 2023-05-30 21:03
 **/
public class Preorder {

    /**
     * 递归前序遍历
     * 中左右
     */
    public static void preOrder(TreeNode root){
        if(root == null){
            return;
        }
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 非递归中序遍历
     */
    public static void preOrderUnRecur(TreeNode root) {
        if(root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur!=null){
            if(cur != null){
                System.out.print(cur.val + " ");
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                cur = cur.right;
            }
        }
    }

}
