package com.yq.ds.tree;

import java.util.Stack;

/**
 * @program: JavaDataStructure
 * @description: 中序遍历
 * @author: Yuqing
 * @create: 2023-05-30 21:32
 **/
public class Inorder {

    /**
     * 递归中序遍历
     * 左中右
     */
    public static void inOrder(TreeNode root){
        if(root == null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    /**
     * 非递归中序遍历
     */
    public static void inOrderUnRecur(TreeNode root){
        if(root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur!=null){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                System.out.print(cur.val + " ");
                cur = cur.right;
            }
        }
    }

}
