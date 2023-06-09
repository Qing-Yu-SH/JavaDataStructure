package com.yq.ds.tree;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void test_morris(){
        int[] preorder = new int[]{1,2,4,7,3,5,6};
        int[] inorder = new int[]{4,7,2,1,5,3,6};
        TreeNode root = ConstructTree.constructByPreAndIn(preorder, inorder);

        System.out.print("前序遍历：");
        Morrisorder.morrisPre(root);

        System.out.print("中序遍历：");
        Morrisorder.morrisIn(root);

        System.out.print("后序遍历：");
        Morrisorder.morrisPos(root);

    }

    @Test
    public void test_thrTree(){
        int[] preorder = new int[]{1,2,4,7,3,5,6};
        int[] inorder = new int[]{4,7,2,1,5,3,6};
        TreeNode root = ConstructTree.constructByPreAndIn(preorder, inorder);
        ThrTree thrTree = new ThrTree();
        thrTree.InOrderThreading(root);
        thrTree.InOrderTraverse_Thr();
    }

    @Test
    public void test_forestConverter(){
        Forest<String> forest = constructForest();
        ForestConverter<String> converter = new ForestConverter<>(forest);
        TreeNode<String> tree = converter.Tree2BinaryTree(forest.trees.get(2));
        Inorder.inOrder(tree);
        System.out.println();
        TreeNode<String> root = converter.Forest2BinaryTree();
        Inorder.inOrder(root);
    }

    private Forest constructForest(){
        MultipleTreeNode<String> nodeA = new MultipleTreeNode<String>("A");
        MultipleTreeNode<String> nodeB = new MultipleTreeNode<String>("B");
        MultipleTreeNode<String> nodeC = new MultipleTreeNode<String>("C");
        MultipleTreeNode<String> nodeD = new MultipleTreeNode<String>("D");
        nodeA.children.add(nodeB);
        nodeA.children.add(nodeC);
        nodeA.children.add(nodeD);

        MultipleTreeNode<String> nodeE = new MultipleTreeNode<String>("E");
        MultipleTreeNode<String> nodeF = new MultipleTreeNode<String>("F");
        MultipleTreeNode<String> nodeG = new MultipleTreeNode<String>("G");
        nodeE.children.add(nodeF);
        nodeE.children.add(nodeG);

        MultipleTreeNode<String> nodeH = new MultipleTreeNode<String>("H");
        MultipleTreeNode<String> nodeI = new MultipleTreeNode<String>("I");
        MultipleTreeNode<String> nodeJ = new MultipleTreeNode<String>("J");
        nodeI.children.add(nodeJ);
        nodeH.children.add(nodeI);

        Forest<String> forest = new Forest<>();
        forest.addTree(nodeA).addTree(nodeE).addTree(nodeH);
        return forest;
    }

    @Test
    public void test_huffmanTree(){
        Map<String,Integer> map = new HashMap<>();
        map.put("A",7);
        map.put("B",5);
        map.put("C",2);
        map.put("D",6);
        map.put("E",1);
        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.create(map);
        Map<String, String> huffmanCode = huffmanTree.getHuffmanCode();
        for(String node: huffmanCode.keySet()){
            System.out.println(node + "：" + huffmanCode.get(node));
        }
    }
}
