package com.yq.ds.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: JavaDataStructure
 * @description: 孩子兄弟法
 * @author: Yuqing
 * @create: 2023-06-07 22:23
 **/
public class TreeChildrenBrother<E> {

    private Node root;
    private E rootVal;
    private int nodeNum;

    /**
     * 孩子兄弟法存储树
     * @param map key - 节点；value - 父节点
     * @return 根节点
     */
    public Node<E> construct(Map<E,E> map){
        this.nodeNum = map.size();
        Map<E, List<E>> eListMap = adjust(map);
        // key - 节点值；value - 节点
        Map<E,Node<E>> val2Node = new HashMap<>();
        for(E parVal: eListMap.keySet()){
            val2Node.putIfAbsent(parVal, new Node<E>(parVal));
            Node<E> parent = val2Node.get(parVal);
            if(parVal.equals(rootVal)){
                this.root = parent;
            }
            List<E> childList = eListMap.get(parVal);
            Node<E> dummyNode = new Node<E>(null);
            Node<E> cur = dummyNode;
            // 将兄弟节点拼接起来
            for(E childVal: childList){
                val2Node.putIfAbsent(childVal, new Node<>(childVal));
                Node<E> child = val2Node.get(childVal);
                        cur.right = child;
                cur = cur.right;
            }
            cur.right = null;
            parent.left = dummyNode.right;
        }
        return root;
    }

    /**
     * Map<E, List<E>>      key - 节点值；value - 孩子节点值
     */
    private Map<E, List<E>> adjust(Map<E,E> map){
        Map<E, List<E>> parentMap = new HashMap<>();
        for(E child: map.keySet()){
            parentMap.putIfAbsent(child,new ArrayList<>());
            E parVal = map.get(child);
            if(parVal == null){
                this.rootVal = child;
                continue;
            }
            List<E> children = parentMap.putIfAbsent(parVal, new ArrayList<>());
            children.add(child);
        }
        return parentMap;
    }

    public void printTree(Node node){
        if(node == null) return;
        printTree(node.left);
        System.out.print(node.data + "\t");
        printTree(node.right);
    }

    class Node<E>{
        private E data;
        // 孩子指针域
        private Node left;
        // 兄弟指针域
        private Node right;

        public Node(E data) {
            this.data = data;
        }
    }
}
