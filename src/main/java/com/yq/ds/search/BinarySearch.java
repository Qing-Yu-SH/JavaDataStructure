package com.yq.ds.search;

/**
 * @program: JavaDataStructure
 * @description: 二分查找
 * @author: Yuqing
 * @create: 2023-06-18 19:37
 **/
public class BinarySearch {

    /**
     * 二分查找，查找一个数
     */
    public <E extends Comparable> int binarySearch(E[] elements,E target){
        int left = 0;
        int right = elements.length - 1;

        while (left <= right){
            int mid = (right-left)/2 + left;
            if(elements[mid].compareTo(target) == 0){
                return mid;
            }else if(elements[mid].compareTo(target) == -1){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 寻找左侧边界的二分搜索
     */
    public <E extends Comparable> int binarySearch_LeftBound(E[] elements,E target){
        int left = 0;
        int right = elements.length - 1;

        while(left <= right){
            int mid = (right-left)/2 + left;
            if(elements[mid].compareTo(target) == -1){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        if(left >= elements.length) return -1;
        return elements[left].compareTo(target)==0 ? left:-1;
    }

    /**
     * 寻找右侧边界的二分查找
     */
    public <E extends Comparable> int binarySearch_RightBound(E[] elements,E target){
        int left = 0;
        int right = elements.length - 1;

        while (left <= right){
            int mid = (right-left)/2 + left;
            if(elements[mid].compareTo(target) <= 0){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        if(left-1 < 0) return -1;
        return elements[left-1].compareTo(target)==0 ? left-1:-1;
    }
}
