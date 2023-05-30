package com.yq.ds.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @program: JavaDataStructure
 * @description: 后序遍历
 * @author: Yuqing
 * @create: 2023-05-30 21:35
 **/
public class Postorder {

    /**
     * 递归后序遍历
     * 左右中
     */
    public static void postOrder(TreeNode root){
        if(root == null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    /**
     * 非递归后序遍历
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root==null) return ans;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        //中右左
        while(!stack.isEmpty() || cur!=null){
            if(cur!=null){
                ans.add(cur.val);
                stack.push(cur);
                cur = cur.right;
            }else{
                cur = stack.pop();
                cur = cur.left;
            }
        }
        //左右中
        Collections.reverse(ans);
        return ans;
    }

}
