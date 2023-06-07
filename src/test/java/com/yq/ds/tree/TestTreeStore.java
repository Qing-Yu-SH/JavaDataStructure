package com.yq.ds.tree;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: JavaDataStructure
 * @description:
 * @author: Yuqing
 * @create: 2023-06-01 10:18
 **/
public class TestTreeStore {

    @Test
    public void test_sequentialStorage(){
        // 顺序存储结构
        int[] preorder = new int[]{1,2,4,7,3,5,6};
        int[] inorder = new int[]{4,7,2,1,5,3,6};
        TreeNode root = ConstructTree.constructByPreAndIn(preorder, inorder);
        TreeSequentialStorage storage = new TreeSequentialStorage(root);
        int[] nodes = storage.getNodes();
        System.out.println(Arrays.toString(nodes));
    }

    @Test
    public void test_treeParent(){
        // 双亲表示法
        TreeParent<Character> tf = new TreeParent<>();
        tf.addNode('A',null);
        tf.addNode('B','A');
        tf.addNode('C','A');
        tf.addNode('D','A');
        tf.addNode('E','B');
        tf.addNode('F','D');
        tf.addNode('G','F');
        System.out.println("树中结点数量：\t" + tf.getNodeNums());
        System.out.println("树是否为空：\t" + tf.isEmpty());
        System.out.println("树的高度为：\t" + tf.deep());
        System.out.println("节点 E 的父节点：\t" + tf.parent('E'));
        System.out.println("根节点信息：\t" + tf.root());
        System.out.println("根节点的孩子：\t" + tf.children(tf.getNode(0)));
    }

    @Test
    public void test_treeChildren(){
        Map<Character,Character> map = new HashMap<>();
        map.put('A',null);
        map.put('B','A');
        map.put('C','A');
        map.put('D','A');
        map.put('E','B');
        map.put('F','D');
        map.put('G','F');
        TreeChildren<Character> treeChildren = new TreeChildren<>();
        treeChildren.construct(map);
        treeChildren.printTree();
    }

    @Test
    public void test_treeChildrenBrother(){
        Map<Character,Character> map = new HashMap<>();
        map.put('A',null);
        map.put('B','A');
        map.put('C','A');
        map.put('D','A');
        map.put('E','B');
        map.put('F','D');
        map.put('G','F');
        TreeChildrenBrother<Character> childrenBrother = new TreeChildrenBrother<>();
        TreeChildrenBrother<Character>.Node<Character> root = childrenBrother.construct(map);
        childrenBrother.printTree(root);
    }

}
