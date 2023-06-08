package com.yq.ds.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: JavaDataStructure
 * @description: 森林转二叉树
 * @author: Yuqing
 * @create: 2023-06-08 23:26
 **/
public class ForestConverter<E> {

    Forest<E> forest;

    public ForestConverter(Forest<E> forest) {
        this.forest = forest;
    }

    public TreeNode<E> Forest2BinaryTree(){
        assert !forest.trees.isEmpty();
        List<TreeNode<E>> trees = new ArrayList<>();
        for(MultipleTreeNode<E> tree: forest.trees){
            trees.add(Tree2BinaryTree(tree));
        }

        TreeNode<E> root = trees.get(0);
        TreeNode<E> cur = root;
        for(int i=1;i<trees.size();i++){
            cur.right = trees.get(i);
            cur = cur.right;
            cur.right = null;
        }

        return root;
    }

    /**
     * 将多叉树转换为二叉树
     * @param root 多叉树
     * @return 二叉树
     */
    public TreeNode<E> Tree2BinaryTree(MultipleTreeNode<E> root){
        if(root == null) return null;
        List<TreeNode<E>> children = new ArrayList<>();
        for(MultipleTreeNode<E> child: root.children){
            children.add(Tree2BinaryTree(child));
        }
        TreeNode<E> child = null;
        // 将兄弟节点连接起来
        if(!children.isEmpty()){
            child = children.get(0);
            TreeNode<E> cur = child;
            for(int i=1;i<children.size();i++){
                cur.right = children.get(i);
                cur = cur.right;
            }
            cur.right = null;
        }
        TreeNode<E> node = new TreeNode<>(root.data);
        node.left = child;
        return node;
    }

}

class Forest<E> {
    List<MultipleTreeNode<E>> trees;

    public Forest() {
        this.trees = new ArrayList<>();
    }

    public Forest<E> addTree(MultipleTreeNode<E> root) {
        trees.add(root);
        return this;
    }

}


class MultipleTreeNode<E>{
    E data;
    List<MultipleTreeNode<E>> children;

    public MultipleTreeNode(E data) {
        this.data = data;
        this.children = new ArrayList<>();
    }
}