package com.yq.ds.tree;

import org.junit.Test;

/**
 * @program: JavaDataStructure
 * @description:
 * @author: Yuqing
 * @create: 2023-06-01 10:18
 **/
public class TestTreeStore {

    @Test
    public void test_treeParent(){
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

}
