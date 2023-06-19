package com.yq.ds.search;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: JavaDataStructure
 * @description: 平衡二叉树
 * @author: Yuqing
 * @create: 2023-06-19 20:16
 **/
public class BalancedBinaryTree<E extends Comparable> {

    // 根节点
    private TreeNode<E> root;
    // 节点高度缓存
    private Map<TreeNode<E>, Integer> nodeHeight;
    // 值与节点映射
    private Map<E, TreeNode<E>> val2Node;

    public BalancedBinaryTree() {
        nodeHeight = new HashMap<>();
        val2Node = new HashMap<>();
    }

    /**
     * 创建 AVL 树
     * @param elements 元素
     */
    public void createAVL(E[] elements){
        for(E element: elements){
            root = insert(root, element);
        }
    }

    private TreeNode<E> insert(TreeNode<E> node,E val) {
        if (node == null) {
            node = new TreeNode<>(val);
            nodeHeight.put(node, 1);// 新节点的高度
            val2Node.put(val,node);
            return node;
        }
        // 比较
        int cmp = node.val.compareTo(val);
        if (cmp > 0) {
            // 左子树插入
            node.left = insert(node.left, val);
            // 如果左右子树高度差超过1，进行旋转调整
            if (nodeHeight.getOrDefault(node.left, 0) -
                    nodeHeight.getOrDefault(node.right, 0) > 1) {
                // LR型
                if (node.left.val.compareTo(val) < 0) {
                    // 插入在左孩子右边，左孩子先左旋
                    node.left = rotateLeft(node.left);
                }
                // LL型
                // 节点右旋
                node = rotateRight(node);
            }
        } else if (cmp < 0) {
            // 右子树插入
            node.right = insert(node.right, val);
            // 如果左右子树高度差超过1，进行旋转调整
            if (nodeHeight.getOrDefault(node.right, 0) -
                    nodeHeight.getOrDefault(node.left, 0) > 1) {
                // RL型
                if (node.right.val.compareTo(val) > 0) {
                    // 插入在右孩子左边，右孩子先右旋
                    node.right = rotateRight(node.right);
                }
                // RR型
                // 节点左旋
                node = rotateLeft(node);
            }
        } else {
            // 一样的节点，啥都没发生
            return node;
        }
        // 获取当前节点新高度
        int height = getCurNodeNewHeight(node, nodeHeight);
        // 更新当前节点高度，每次插入时，都会递归进行更新
        nodeHeight.put(node, height);
        return node;
    }

    /**
     * node节点左旋，逆时针方向，RR型
     * 只需更新 原根节点和新根节点 高度
     * @param node       node
     * @return 旋转后的当前节点
     */
    private TreeNode<E> rotateLeft(TreeNode<E> node) {
        // ---指针调整
        TreeNode<E> right = node.right;
        node.right = right.left;
        right.left = node;
        // ---高度更新
        // 先更新 node 节点的高度，这个时候 node 是 right 节点的左孩子
        int newNodeHeight = getCurNodeNewHeight(node, nodeHeight);
        // 更新 node 节点高度
        nodeHeight.put(node, newNodeHeight);
        // newNodeHeight 是现在 right 节点左子树高度，原理一样，取现在 right 左右子树最大高度+1
        int newRightHeight = Math.max(newNodeHeight,
                nodeHeight.getOrDefault(right.right, 0)) + 1;
        // 更新原 right 节点高度
        nodeHeight.put(right, newRightHeight);
        return right;
    }

    /**
     * node节点右旋，顺时针方向，LL型
     * 只需更新 原根节点和新根节点 高度
     * @param node       node
     * @return 旋转后的当前节点
     */
    private TreeNode<E> rotateRight(TreeNode<E> node){
        // ---指针调整
        TreeNode<E> left = node.left;
        node.left = left.right;
        left.right = node;
        // ---高度更新
        // 先更新 node 节点的高度，这个时候 node 是 left 节点的右孩子
        int newNodeHeight = getCurNodeNewHeight(node, nodeHeight);
        // 更新 node 节点高度
        nodeHeight.put(node, newNodeHeight);
        // newNodeHeight 是现在 left 节点右子树高度，原理一样，取现在 left 左右子树最大高度+1
        int newLeftHeight = Math.max(newNodeHeight,
                nodeHeight.getOrDefault(left.left, 0)) + 1;
        // 更新原 left 节点高度
        nodeHeight.put(left, newLeftHeight);
        return left;
    }


    /**
     * 获取当前节点的新高度
     *
     * @param node       node
     * @param nodeHeight node高度缓存
     * @return 当前node的新高度
     */
    private int getCurNodeNewHeight(TreeNode<E> node, Map<TreeNode<E>, Integer> nodeHeight) {
        // node节点的高度，为现在node左右子树最大高度+1
        return Math.max(nodeHeight.getOrDefault(node.left, 0),
                nodeHeight.getOrDefault(node.right, 0)) + 1;
    }

    /**
     * 查询元素是否在平衡二叉树中
     * @param val 元素
     * @return true or false
     */
    public boolean searchAVL(E val){
        assert val != null;
        TreeNode<E> node = searchInAVL(root,val);
        return node != null;
    }

    private TreeNode<E> searchInAVL(TreeNode<E> node,E val){
        if(node == null){
            return null;
        }
        int cmp = node.val.compareTo(val);
        if(cmp == 0){
            return node;
        }else if(cmp > 0){
            return searchInAVL(node.left,val);
        }else{
            return searchInAVL(node.right,val);
        }
    }

    /**
     * 获取节点高度
     * @param val 节点元素值
     * @return 节点高度
     */
    public int getHeight(E val){
        if(!val2Node.containsKey(val)){
            return -1;
        }
        TreeNode<E> node = val2Node.get(val);
        return nodeHeight.get(node);
    }

}
