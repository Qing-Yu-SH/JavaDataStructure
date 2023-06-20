package com.yq.ds.search;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @program: JavaDataStructure
 * @description:
 * @author: Yuqing
 * @create: 2023-06-18 18:35
 **/
public class TestSearch {

    long startTime = 0;
    long endTime = 0;

    @Before
    public void start(){
        startTime = System.nanoTime();
    }

    @After
    public void end(){
        endTime = System.nanoTime();
        System.out.printf("执行时长：%.2f 微秒.", (endTime - startTime)/1000.0);
    }

    @Test
    public void test_orderSearchWithSentinel(){
        // 位置 0 不放元素
        Integer[] elements = new Integer[]{0,5,2,6,32,8,9,12,22,52,62,72,86,96,100,102,106,112,200,105,108};
        OrderSearch orderSearch = new OrderSearch();
        int pos = orderSearch.orderSearch(elements, 2);
        System.out.println("元素 2 的位置：" + pos);
        int pos2 = orderSearch.orderSearch(elements, 220);
        System.out.println("元素 220 的位置：" + pos2);
    }

    @Test
    public void test_orderSearchWithoutSentinel(){
        // 位置 0 不放元素
        Integer[] elements = new Integer[]{0,5,2,6,32,8,9,12,22,52,62,72,86,96,100,102,106,112,200,105,108};
        OrderSearch orderSearch = new OrderSearch();
        int pos = orderSearch.orderSearchWithoutSentinel(elements, 2);
        System.out.println("元素 2 的位置：" + pos);
        int pos2 = orderSearch.orderSearchWithoutSentinel(elements, 220);
        System.out.println("元素 220 的位置：" + pos2);
    }

    @Test
    public void test_binarySearch(){
        Integer[] elements = new Integer[]{0,2,5,6,6,6,8,9,10,12,16,22,26,32,36,38,52,56,62,66,72,82};
        BinarySearch binarySearch = new BinarySearch();
        int pos = binarySearch.binarySearch(elements, 6);
        System.out.println("元素 6 位置：" + pos);
        int pos2 = binarySearch.binarySearch_LeftBound(elements,6);
        System.out.println("元素 6 左侧位置：" + pos2);
        int pos3 = binarySearch.binarySearch_RightBound(elements,6);
        System.out.println("元素 6 右侧位置：" + pos3);
    }

    @Test
    public void test_blockSearch(){
        // 22 46 66 82 100
        Integer[] elements = new Integer[]{20,22,2,16,6, 26,46,32,36,28, 50,62,55,56,66, 72,68,70,69,82, 85,96,92,100,86};
        BlockSearch.BlockTable[] blockTables = new BlockSearch.BlockTable[]{
                new BlockSearch.BlockTable(1,0,4),
                new BlockSearch.BlockTable(6,5,9),
                new BlockSearch.BlockTable(14,10,14),
                new BlockSearch.BlockTable(19,15,19),
                new BlockSearch.BlockTable(23,20,24)
        };
        BlockSearch<Integer> blockSearch = new BlockSearch<>(elements,blockTables);
        int pos = blockSearch.blockSearch(0);
        System.out.println("元素 0 位置：" + (pos==-1 ? "不存在":pos));
        int pos2 = blockSearch.blockSearch(2);
        System.out.println("元素 2 位置：" + pos2);
        int pos3 = blockSearch.blockSearch(22);
        System.out.println("元素 22 位置：" + pos3);
        int pos4 = blockSearch.blockSearch(26);
        System.out.println("元素 26 位置：" + pos4);
        int pos5 = blockSearch.blockSearch(52);
        System.out.println("元素 52 位置：" + (pos5==-1 ? "不存在":pos5));
        int pos6 = blockSearch.blockSearch(66);
        System.out.println("元素 66 位置：" + pos6);
        int pos7 = blockSearch.blockSearch(68);
        System.out.println("元素 68 位置：" + pos7);
        int pos8 = blockSearch.blockSearch(86);
        System.out.println("元素 86 位置：" + pos8);
        int pos9 = blockSearch.blockSearch(102);
        System.out.println("元素 102 位置：" + (pos9==-1 ? "不存在":pos9));
    }

    @Test
    public void test_binarySortTree(){
        Integer[] elements = new Integer[]{0,5,2,6,32,8,9,12,22,52,62,72,86,96,100,102,106,112,200,105,108};
        BinarySortTree<Integer> binarySortTree = new BinarySortTree<>(elements);
        boolean pos = binarySortTree.searchInBST(2);
        System.out.println("元素 2 是否存在：" + pos);
        boolean pos2 = binarySortTree.searchInBST(220);
        System.out.println("元素 220 是否存在：" + pos2);
        binarySortTree.addNode(220);
        boolean pos3 = binarySortTree.searchInBST(220);
        System.out.println("元素 220 是否存在：" + pos3);
        binarySortTree.deleteNode(220);
        boolean pos4 = binarySortTree.searchInBST(220);
        System.out.println("元素 220 是否存在：" + pos4);
    }

    @Test
    public void test_balanceBinaryTree(){
        Integer[] elements = new Integer[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,18,19,20,21,22};
        BalancedBinaryTree<Integer> balancedBinaryTree = new BalancedBinaryTree<>();
        balancedBinaryTree.createAVL(elements);
        int height = balancedBinaryTree.getHeight(22);
        System.out.println("元素 22 所在高度：" + height);
        int height2 = balancedBinaryTree.getHeight(7);
        System.out.println("元素 7 所在高度：" + height2);
        boolean pos = balancedBinaryTree.searchAVL(20);
        System.out.println("元素 20 是否存在：" + pos);
    }

    @Test
    public void test_bTree(){
        Integer[] elements = new Integer[]{46,22,2,12,37,53,90,50,62,70,100,32,26,85,7};
        BTree<Integer> bTree = new BTree<>();
        for(Integer element: elements){
            bTree.insertKey(element);
        }
        boolean pos = bTree.search(62);
        System.out.println("元素 62 是否存在：" + pos);
        boolean pos2 = bTree.search(50);
        System.out.println("元素 50 是否存在：" + pos2);
        bTree.deleteKey(50);
        boolean pos3 = bTree.search(50);
        System.out.println("元素 50 是否存在：" + pos3);
        boolean pos4 = bTree.search(90);
        System.out.println("元素 90 是否存在：" + pos4);
        bTree.deleteKey(90);
        boolean pos5 = bTree.search(90);
        System.out.println("元素 90 是否存在：" + pos5);
        boolean pos6 = bTree.search(102);
        System.out.println("元素 102 是否存在：" + pos6);
    }

    @Test
    public void test_hashTable(){
        Integer[] elements = new Integer[]{22,22,2,12,36,53,220};
        HashTable<Integer> hashTable = new HashTable<>(20);
        for (Integer element: elements){
            boolean insert = hashTable.insert(element);
            System.out.println("插入元素 " + element + " ：" + (insert ? "成功":"失败"));
        }
        int pos = hashTable.select(22);
        System.out.println("元素 22 的位置：" + pos);
        int pos2 = hashTable.select(2);
        System.out.println("元素 2 的位置： " + pos2);
        int pos3 = hashTable.select(36);
        System.out.println("元素 36 的位置： " + pos3);
    }
}
