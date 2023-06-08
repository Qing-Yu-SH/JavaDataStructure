package com.yq.ds.tree;

/**
 * @program: JavaDataStructure
 * @description: 线索二叉树
 * @author: Yuqing
 * @create: 2023-06-05 13:17
 **/
public class ThrTree<E> {

    // 全局变量，指示遍历过程中的线索二叉树的前驱结点信息
    private BiThrNode preBN = null;
    // 全局变量，指示遍历过程中的二叉树的前驱结点信息
    private TreeNode preTN = null;

    // 头结点
    private BiThrNode thr;

    public ThrTree() {
        // 建立头结点
        thr = new BiThrNode();
    }

    /**
     * 中序线索化
     */
    BiThrNode InThreading(TreeNode<E> root){
        if(root == null) return null;
        BiThrNode<E> node = new BiThrNode<>(root.val);

        // 左子树递归线索化
        node.left = InThreading(root.left);

        // 左孩子为空时，加上左线索
        if(root.left == null){
            node.LTag = 1;
            node.left = preBN;
        }else{
            node.LTag = 0;
        }
        if(preTN!=null){
            // pre 右孩子为空时，加上右线索
            if(preTN.right == null){
                preBN.RTag = 1;
                preBN.right = node;
            }else{
                preBN.RTag = 0;
            }
        }
        preBN = node;
        preTN = root;

        // 右子树递归线索化
        node.right = InThreading(root.right);

        return node;
    }

    /**
     * 带头结点的二叉树中序线索化
     */
    void InOrderThreading(TreeNode<E> root){
        // 头结点有左孩子，若树非空，则其左孩子为树根
        thr.LTag = 0;
        // 头结点的右孩子指针为右线索
        thr.RTag = 1;
        // 初始化时，右指针指向自己
        thr.right = thr;
        if(root == null){
            // 若树为空，则左指针也指向自己
            thr.left = thr;
        }else{
            // pre 初值指向头结点
            preBN = thr;
            // 头结点的左孩子指向
            thr.left = InThreading(root);

            // pre 为最右结点，pre 的右线索指向头结点
            preBN.right = thr;
            preBN.RTag = 1;
            // 头结点的右线索指向 pre
            thr.right = preBN;
        }
    }

    /**
     * 遍历中序线索二叉树
     */
     void InOrderTraverse_Thr(){
        BiThrNode p = thr.left;
        while (p != thr){
            // 沿左孩子向下
            while (p.LTag == 0){
                p = p.left;
            }
            // 访问其左孩子为空的结点
            System.out.println(p.val);
            // 沿右线索访问后继结点
            while (p.RTag==1 && p.right!=thr){
                p = p.right;
                System.out.println(p.val);
            }
            // 转向右子树
            p = p.right;
        }
    }

    public BiThrNode getRoot() {
        return thr;
    }

}

/**
 * 线索二叉树节点
 */
class BiThrNode<E>{
    E val;
    BiThrNode<E> left;
    BiThrNode<E> right;
    int LTag,RTag;

    public BiThrNode() {
    }

    public BiThrNode(E val) {
        this.val = val;
    }
}
