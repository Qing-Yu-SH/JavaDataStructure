package com.yq.ds.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @program: JavaDataStructure
 * @description: 哈夫曼树
 * @author: Yuqing
 * @create: 2023-06-09 21:46
 **/
public class HuffmanTree {

    private HuffmanNode root;
    private Map<String,String> nodeCode;

    public HuffmanTree() {
        this.root = null;
        this.nodeCode = new HashMap<>();
    }

    /**
     * 构造哈夫曼树
     * @param nodes name -> weight
     */
    public void create(Map<String,Integer> nodes){
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>((a, b) -> a.weight-b.weight);
        for(Map.Entry<String,Integer> entry: nodes.entrySet()){
            pq.offer(new HuffmanNode(entry.getKey(),entry.getValue()));
        }

        // 构造哈夫曼树
        while(!pq.isEmpty() && pq.size()>1){
            // 出队权值最小的两个结点
            HuffmanNode child = pq.poll();
            HuffmanNode child2 = pq.poll();
            HuffmanNode parent = new HuffmanNode(child.weight+child2.weight);
            parent.left = child;
            parent.right = child2;
            pq.offer(parent);
        }

        root = pq.poll();
    }

    /**
     * 获取哈夫曼编码
     * @return name -> 哈夫曼编码
     */
    public Map<String,String> getHuffmanCode(){
        if(nodeCode.isEmpty()){
            preorder(root,null,new StringBuilder());
        }
        return nodeCode;
    }

    /**
     * 先序遍历哈夫曼树，并构造哈夫曼编码
     */
    private void preorder(HuffmanNode node,HuffmanNode pre,StringBuilder builder){
        if(node == null) return;
        if(pre != null){
            if(pre.left == node) builder.append("0");
            if(pre.right == node) builder.append("1");
        }
        if(node.left==null && node.right==null){
            nodeCode.put(node.name,builder.toString());
            return;
        }
        preorder(node.left,node,builder);
        builder.deleteCharAt(builder.length()-1);
        preorder(node.right,node,builder);
        builder.deleteCharAt(builder.length()-1);
    }

}

class HuffmanNode{
    String name;
    int weight;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode() {
    }

    public HuffmanNode(int weight) {
        this.weight = weight;
        this.name = null;
        this.left = null;
        this.right = null;
    }

    public HuffmanNode(String name, int weight) {
        this.name = name;
        this.weight = weight;
        this.left = null;
        this.right = null;
    }
}
