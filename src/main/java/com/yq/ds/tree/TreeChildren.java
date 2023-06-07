package com.yq.ds.tree;

import java.util.Map;

/**
 * @program: JavaDataStructure
 * @description: 孩子表示法
 * @author: Yuqing
 * @create: 2023-06-07 21:29
 **/
public class TreeChildren<E> {

    private Node[] nodes;
    private int nodeNum;
    private int curNum;

    /**
     * 孩子表示法存储树
     * @param map key - 孩子节点；value - 父亲节点
     */
    public void construct(Map<E,E> map){
        this.nodeNum = map.size();
        this.nodes = new Node[nodeNum];
        curNum = 0;

        for(E val: map.keySet()){
            // 获取父节点值
            E parVal = map.get(val);
            // 获取节点位置
            int nodeIndex = locNode(val);
            if(parVal != null){
                // 存在父节点，将边加入父节点的边表
                int parentIndex = locNode(parVal);
                Node parent = nodes[parentIndex];
                if(parent.firstArc == null){
                    parent.firstArc = new Edge(nodeIndex,null);
                }else{
                    Edge edge = parent.firstArc;
                    while(edge.nextArc != null){
                        edge = edge.nextArc;
                    }
                    edge.nextArc = new Edge(nodeIndex,null);
                }
            }else{
                // 不存在父亲节，则该节点是根节点
                nodes[nodeIndex].isRoot = true;
            }
        }
    }

    /**
     * 根据 val 定位寻找节点，找不到则新建节点
     */
    private int locNode(E val){
        for(int i=0;i<curNum;i++){
            if(val.equals(nodes[i].data)){
                return i;
            }
        }
        nodes[curNum++] = new Node(val);
        return curNum-1;
    }


    public void printTree(){
        for(int i=0;i<nodeNum;i++){
            Node node = nodes[i];
            System.out.print("索引：" + i + "\t 节点：" + node.data + "\t 边表：");
            Edge edge = node.firstArc;
            StringBuilder sb = new StringBuilder();
            while(edge != null){
                sb.append(edge.dataLoc);
                edge = edge.nextArc;
                if(edge != null) sb.append(" -> ");
            }
            System.out.println(sb.toString());
        }
    }


    class Node<E>{
        private E data;
        private Edge firstArc;
        private boolean isRoot;

        public Node(E data) {
            this.data = data;
            this.isRoot = false;
        }

        public Node(E data, boolean isRoot) {
            this.data = data;
            this.isRoot = isRoot;
        }

        public Node(E data, Edge firstArc, boolean isRoot) {
            this.data = data;
            this.firstArc = firstArc;
            this.isRoot = isRoot;
        }
    }

    class Edge{
        private int dataLoc;
        private Edge nextArc;

        public Edge(int dataLoc) {
            this.dataLoc = dataLoc;
        }

        public Edge(int dataLoc, Edge nextArc) {
            this.dataLoc = dataLoc;
            this.nextArc = nextArc;
        }
    }
}
