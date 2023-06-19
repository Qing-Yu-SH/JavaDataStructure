package com.yq.ds.search;

/**
 * @program: JavaDataStructure
 * @description: 二叉排序树
 * @author: Yuqing
 * @create: 2023-06-19 18:52
 **/
public class BinarySortTree<E extends Comparable> {

    private TreeNode<E> root;

    public BinarySortTree(E[] elements) {
        this.root = createBST(elements);
    }

    /**
     * BST 创建
     */
    private TreeNode<E> createBST(E[] elements){
        assert elements!=null && elements.length>0;
        TreeNode<E> root = new TreeNode<>(elements[0]);
        for(int i=1;i<elements.length;i++){
            insertBST(root,elements[i]);
        }
        return root;
    }

    private TreeNode<E> insertBST(TreeNode<E> root, E val){
        if(root == null){
            return new TreeNode<>(val);
        }
        if(root.val.compareTo(val) < 0){
            root.right = insertBST(root.right,val);
        }else if(root.val.compareTo(val) > 0){
            root.left = insertBST(root.left,val);
        }
        return root;
    }

    public boolean searchInBST(E key){
        TreeNode<E> treeNode = search_BST(root,key);
        return treeNode != null;
    }

    /**
     * BST 查找
     * @return 查询的节点
     */
    private TreeNode<E> search_BST(TreeNode<E>root,E key){
        if(root == null){
            return null;
        }
        if(root.val.compareTo(key) == 0){
            return root;
        }else if(root.val.compareTo(key) < 0){
            return search_BST(root.right,key);
        }else{
            return search_BST(root.left,key);
        }
    }


    class TreeNode<E extends Comparable> {
        E val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(E val) { this.val = val; }
        TreeNode(E val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
