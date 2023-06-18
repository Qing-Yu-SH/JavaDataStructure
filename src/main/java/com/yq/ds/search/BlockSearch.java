package com.yq.ds.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: JavaDataStructure
 * @description: 分块查找
 * @author: Yuqing
 * @create: 2023-06-18 20:32
 **/
public class BlockSearch<E extends Comparable> {

    // 元素表
    private E[] elements;
    // 索引块数量
    private int blockNum;
    // 索引表
    private BlockTable[] blockTables;

    public BlockSearch(E[] elements, BlockTable[] blockTables) {
        this.elements = elements;
        this.blockTables = blockTables;
        this.blockNum = blockTables.length;
    }

    public int blockSearch(E target){
        if(target == null) return -1;
        // 在索引表中二分查找
        int indexTable = searchInBlockTable(target);
        if(indexTable == blockNum) return -1;
        return searchInBlock(indexTable,target);
    }

    /**
     * 索引表二分查找
     */
    private int searchInBlockTable(E target){
        int left = 0,right = blockNum - 1;
        while(left <= right){
            int mid = (right-left)/2 + left;
            int midIndex = blockTables[mid].maxElement;
            if(elements[midIndex].compareTo(target) == 0){
                return mid;
            }else if(elements[midIndex].compareTo(target) < 0){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 块内顺序查找
     */
    private int searchInBlock(int indexBlock,E target){
        BlockTable blockTable = blockTables[indexBlock];
        int start = blockTable.startIndex,end = blockTable.rightIndex;
        for(int i=start;i<=end;i++){
            if(elements[i].compareTo(target) == 0){
                return i;
            }
        }
        return -1;
    }

    static class BlockTable{
        int maxElement;
        int startIndex;
        int rightIndex;

        public BlockTable(int maxElement, int startIndex, int rightIndex) {
            this.maxElement = maxElement;
            this.startIndex = startIndex;
            this.rightIndex = rightIndex;
        }
    }
}
