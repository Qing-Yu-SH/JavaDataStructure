package com.yq.ds.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: JavaDataStructure
 * @description: 构造二叉树；要求二叉树中元素不重复
 * @author: Yuqing
 * @create: 2023-05-30 21:03
 **/
public class ConstructTree {

    /**
     * 通过前序遍历和中序遍历构建二叉树
     * @param preorder 前序遍历
     * @param inorder  中序遍历
     * @return
     */
    public static TreeNode constructByPreAndIn(int[] preorder,int[] inorder){
        assert preorder.length == inorder.length;
        int n = preorder.length;
        Map<Integer,Integer> val2Index = new HashMap<>();
        for(int i=0;i<n;i++){
            val2Index.put(inorder[i],i);
        }
        return constructPre(preorder,0,n-1,inorder,0,n-1,val2Index);
    }

    private static TreeNode constructPre(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> val2Index){
        if(preStart > preEnd) return null;
        int val = preorder[preStart];
        TreeNode node = new TreeNode(val);
        int inIndex = val2Index.get(val);
        int leftNum = inIndex - inStart;
        node.left = constructPre(preorder,preStart+1,preStart+leftNum,inorder,inStart,inIndex-1,val2Index);
        node.right = constructPre(preorder,preStart+leftNum+1,preEnd,inorder,inIndex+1,inEnd,val2Index);
        return node;
    }

    /**
     * 通过后序遍历和中序遍历构建二叉树
     * @param postorder  后序遍历
     * @param inorder    中序遍历
     * @return
     */
    public static TreeNode constructByPostAndIn(int[] postorder,int[] inorder){
        assert postorder.length == inorder.length;
        int n = postorder.length;
        Map<Integer,Integer> val2Index = new HashMap<>();
        for(int i=0;i<n;i++){
            val2Index.put(inorder[i],i);
        }
        return constructPost(postorder,0,n-1,inorder,0,n-1,val2Index);
    }

    private static TreeNode constructPost(int[] postorder,int postStart,int postEnd,int[] inorder,int inStart,int inEnd,Map<Integer,Integer> val2Index){
        if(postStart > postEnd) return null;
        int val = postorder[postEnd];
        TreeNode node = new TreeNode(val);
        int inIndex = val2Index.get(val);
        int leftNum = inIndex - inStart;
        node.left = constructPost(postorder,postStart,postStart+leftNum-1,inorder,inStart,inIndex-1,val2Index);
        node.right = constructPost(postorder,postStart+leftNum,postEnd-1,inorder,inIndex+1,inEnd,val2Index);
        return node;
    }

}
