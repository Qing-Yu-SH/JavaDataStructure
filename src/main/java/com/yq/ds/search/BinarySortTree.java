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

    /**
     * 添加节点
     * @param element 插入的元素
     */
    public void addNode(E element){
        assert element != null;
        insertBST(root,element);
    }

    /**
     * 删除节点
     * @param element 要删除的元素
     */
    public void deleteNode(E element){
        assert element!=null && root!=null;
        TreeNode<E> cur = root;
        TreeNode<E> pre = null;
        // 寻找要删除结点
        while (cur != null){
            if(cur.val.compareTo(element) == 0){
                break;
            }
            pre = cur;
            if(cur.val.compareTo(element) < 0){
                cur = cur.right;
            }else{
                cur = cur.left;
            }
        }
        // 要删除结点不在二叉排序树
        if(cur == null){
            return;
        }
        if(pre == null){
            root = deleteOneNode2(root);
        }
        if(pre.left == cur){
            pre.left = deleteOneNode2(cur);
        }
        if(pre.right == cur){
            pre.right = deleteOneNode2(cur);
        }
    }

    private TreeNode<E> deleteOneNode(TreeNode<E> node){
        if(node == null){
            return null;
        }
        // 情况 2：右子树为空，直接返回其左孩子
        if(node.right == null){
            return node.left;
        }
        // 情况 3：左子树为空，直接返回其右孩子
        if(node.left == null){
            return node.right;
        }
        // 情况 4：左右孩子都非空
        // 找到右子树最左孩子
        TreeNode<E> cur = node.right;
        while (cur.left != null){
            cur = cur.left;
        }
        cur.left = node.left;
        return node.right;
    }

    private TreeNode<E> deleteOneNode2(TreeNode<E> node){
        if(node == null){
            return null;
        }
        // 情况 2
        if(node.right == null){
            return node.left;
        }
        // 情况 3
        if(node.left == null){
            return node.right;
        }
        // 情况 4
        // 寻找左子树最右结点
        TreeNode<E> cur = node.left;
        TreeNode<E> pre = node;
        while(cur.right != null){
            pre = cur;
            cur = cur.right;
        }
        if(pre == node){
            // 左孩子无右子树
            pre.left = cur.left;
        }else{
            pre.right = cur.left;
        }
        // cur 代替 node
        cur.left = node.left;
        cur.right = node.right;
        return cur;
    }


    private class TreeNode<E extends Comparable> {
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
