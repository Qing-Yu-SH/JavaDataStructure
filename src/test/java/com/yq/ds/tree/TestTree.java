package com.yq.ds.tree;

import org.junit.Test;

/**
 * @program: JavaDataStructure
 * @description:
 * @author: Yuqing
 * @create: 2023-05-30 21:37
 **/
public class TestTree {

    @Test
    public void test_order(){
        int[] preorder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};
        int[] postorder = new int[]{9,15,7,20,3};
        TreeNode root = ConstructTree.constructByPreAndIn(preorder, inorder);
        TreeNode root2 = ConstructTree.constructByPostAndIn(postorder, inorder);
        System.out.println("root：");
        System.out.print("前序遍历：");
        Preorder.preOrder(root);
        System.out.println();

        System.out.print("中序遍历：");
        Inorder.inOrder(root);
        System.out.println();

        System.out.print("后序遍历：");
        Postorder.postOrder(root);
        System.out.println();

        System.out.println("root2：");
        System.out.print("前序遍历：");
        Preorder.preOrder(root2);
        System.out.println();

        System.out.print("中序遍历：");
        Inorder.inOrder(root2);
        System.out.println();

        System.out.print("后序遍历：");
        Postorder.postOrder(root2);
        System.out.println();

    }
}
