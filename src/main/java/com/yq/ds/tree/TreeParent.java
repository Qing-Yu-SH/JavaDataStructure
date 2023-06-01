package com.yq.ds.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @program: JavaDataStructure
 * @description: 树的存储结构：双亲表示法
 * @author: Yuqing
 * @create: 2023-06-01 09:14
 **/
public class TreeParent<E> {

    private static final int DEFAULT_TREE_SIZE = 10;

    private static final Node[] DEFAULT_CAPACITY_EMPTY_NODES = {};

    private static final int MAX_TREE_SIZE = Integer.MAX_VALUE - 8;

    // 记录该树里的所有节点
    transient Node<E>[] nodes;

    // 记录当前树中节点
    private int nodeNums;

    // 记录当前树最多可保存多少节点
    private int treeSize;

    public TreeParent() {
        this.nodes = DEFAULT_CAPACITY_EMPTY_NODES;
    }

    // 以指定树中初始节点数量创建树
    public TreeParent(int treeSize) {
        if(treeSize > 0){
            this.treeSize = treeSize;
            this.nodeNums = 0;
            this.nodes = new Node[treeSize];
        }else if(treeSize == 0){
            this.nodes = DEFAULT_CAPACITY_EMPTY_NODES;
        }else{
            throw new IllegalArgumentException("Illegal treeSize: "+
                    treeSize);
        }
    }

    // 以指定根节点创建树
    public TreeParent(E data){
        this.treeSize = DEFAULT_TREE_SIZE;
        nodes = new Node[treeSize];
        nodes[0] = new Node<>(data,-1);
        nodeNums++;
    }

    // 以指定根节点和初始树节点数量创建树
    public TreeParent(E data,int treeSize){
        this.treeSize = treeSize;
        nodes = new Node[treeSize];
        nodes[0] = new Node<>(data,-1);
        nodeNums++;
    }

    public boolean addNode(E e,E parent){
        // 扩容
        ensureCapacityInternal(nodeNums + 1);
        int pos = 0;
        if(parent == null){
            if(nodes[0] != null) return false;
            pos = -1;
        }else{
            pos = pos(parent);
        }
        nodes[nodeNums++] = new Node<>(e,pos);
        return true;
    }

    private void ensureCapacityInternal(int minTreeSize) {
        ensureExplicitCapacity(calculateCapacity(nodes, minTreeSize));
    }

    // 计算所需的最少节点数量
    private static int calculateCapacity(Node[] elementData, int minTreeSize) {
        if (elementData == DEFAULT_CAPACITY_EMPTY_NODES) {
            return Math.max(DEFAULT_TREE_SIZE, minTreeSize);
        }
        return minTreeSize;
    }

    // 判断是否需要扩容
    private void ensureExplicitCapacity(int minTreeSize) {

        // overflow-conscious code
        if (minTreeSize - nodes.length > 0)
            grow(minTreeSize);
    }

    // 扩容
    private void grow(int minTreeSize) {
        // overflow-conscious code
        int oldCapacity = nodes.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minTreeSize < 0)
            newCapacity = minTreeSize;
        if (newCapacity - MAX_TREE_SIZE > 0)
            newCapacity = hugeCapacity(minTreeSize);

        nodes = Arrays.copyOf(nodes, newCapacity);
    }

    private static int hugeCapacity(int minTreeSize) {
        if (minTreeSize < 0) // overflow
            throw new OutOfMemoryError();
        return (minTreeSize > MAX_TREE_SIZE) ?
                Integer.MAX_VALUE :
                MAX_TREE_SIZE;
    }

    // 查找指定元素值在树中的位置
    public int pos(E data){
       if(data == null)
           throw new IllegalArgumentException("节点值不能为空");
       for(int i=0;i<nodeNums;i++){
           if(nodes[i].data.equals(data)){
               return i;
           }
       }
       throw new NoSuchElementException("该节点不在树中");
    }

    // 判断是否为空
    public boolean isEmpty(){
        return nodeNums == 0;
    }

    // 返回根节点
    public Node<E> root(){
        return nodeNums==0 ? null:nodes[0];
    }

    // 返回指定节点的父节点
    public Node<E> parent(E data){
        int pos = pos(data);
        return pos==0 ? null:nodes[nodes[pos].parent];
    }

    // 返回指定节点（非叶子节点）的所有子节点
    public List<Node<E>> children(Node<E> parent){
        List<Node<E>> list = new ArrayList<>();
        int pos = pos(parent.data);
        for(int i=0;i<nodeNums;i++){
            if(nodes[i].parent == pos){
                list.add(nodes[i]);
            }
        }
        return list;
    }

    // 返回该树的深度
    public int deep(){
        int maxDeep = 0;
        for(int i=1;i<nodeNums;i++){
            int parent = nodes[i].parent;
            int deep = 1;
            while(parent != -1){
                parent = nodes[parent].parent;
                deep++;
            }
            maxDeep = Math.max(maxDeep,deep);
        }
        return maxDeep;
    }

    public int getNodeNums(){
        return nodeNums;
    }

    // 根据索引获取节点
    public Node<E> getNode(int index){
        return index<nodeNums ? nodes[index]:null;
    }


    public static class Node<E>{
        // 保存节点值
        E data;
        // 保存其父节点位置
        int parent;

        public Node() {
        }

        public Node(E data) {
            this.data = data;
        }

        public Node(E data, int parent) {
            this.data = data;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", parent=" + parent +
                    '}';
        }
    }
}
