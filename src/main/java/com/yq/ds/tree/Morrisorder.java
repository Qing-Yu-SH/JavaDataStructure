package com.yq.ds.tree;

/**
 * @program: JavaDataStructure
 * @description: Morris 遍历；把空间复杂度优化到O(1)的二叉树遍历算法
 * @author: Yuqing
 * @create: 2023-05-31 15:48
 **/
public class Morrisorder {

    /**
     * 假设当前节点为cur，并且开始时赋值为根节点root(其实也可以直接对root操作)。
     * 循环判断cur不为空
     * if左子树为空，则cur向右孩子移动
     * if左子树不为空，找到左子树的最右节点(也就是左子树一直往右走的，走到到尽头就是它的最右节点)
     * 如果最右节点的右指针为空，则把右指针指向cur，cur向左孩子移动
     * 如果最右节点的右指针为cur本身，则说明已经访问过一遍了，我们将其置为null，cur向右孩子移动
     * cur为空时，退出循环
     *
     * @param root
     */
    public static void morris(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                // 找到cur左子树上最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    // 此时说明节点还未遍历完，所以指向cur
                    mostRight.right = cur;
                    cur = cur.left;
                    // 回到最外层的 while，继续判断 cur 的情况
                    continue;
                } else {
                    // mostRight.right 指向 cur
                    mostRight.right = null;
                }
            }
            // cur 如果没有左子树，cur 向右移动
            // 或者 cur 左子树上最右节点的右指针是指向 cur 的，cur 向右移动
            cur = cur.right;
        }
    }

    /**
     * 根据 Morris 遍历，加工出先序遍历
     * 1．对于 cur 只能到达一次的节点（无左子树的节点），cur 到达时直接打印
     * 2．对于 cur 可以到达两次的节点（有左子树的节点），cur 第一次到达时打印，第二次到达时不打印
     *
     * @param root
     */
    public static void morrisPre(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.print(cur.val + " ");
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                System.out.print(cur.val + " ");
            }
            cur = cur.right;
        }
        System.out.println();
    }

    /**
     * 根据 Morris 遍历，加工出中序遍历
     * 1．对于 cur 只能到达一次的节点（无左子树的节点），cur 到达时直接打印
     * 2．对于 cur 可以到达两次的节点（有左子树的节点），cur 第一次到达时不打印，第二次到达时打印
     *
     * @param root
     */
    public static void morrisIn(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        System.out.println();
    }

    /**
     * 根据 Morris 遍历，加工出后序遍历
     * 1．对于 cur 只能到达一次的节点（无左子树的节点），直接跳过，没有打印行为
     * 2．对于 cur 可以到达两次的任何一个节点（有左子树的节点）X，cur 第一次到达 X 时没有打印行为；当第二次到达 X 时，逆序打印 X 左子树的右边界
     * 3．cur 遍历完成后，逆序打印整棵树的右边界
     *
     * @param root
     */
    public static void morrisPos(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(root);
        System.out.println();
    }

    // 逆序打印子树的右边界
    public static void printEdge(TreeNode from) {
        TreeNode tail = reverseEdge(from);
        TreeNode cur = tail;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    // 子树的右边界就相当于一个单链表，通过节点的 right 指针相连
    public static TreeNode reverseEdge(TreeNode from) {
        TreeNode pre = null;
        TreeNode next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }

}
