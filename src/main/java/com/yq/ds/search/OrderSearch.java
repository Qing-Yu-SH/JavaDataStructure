package com.yq.ds.search;

/**
 * @program: JavaDataStructure
 * @description: 顺序查找
 * @author: Yuqing
 * @create: 2023-06-18 18:28
 **/
public class OrderSearch {

    /**
     * 设置哨兵的顺序查找
     */
    public <E> int orderSearch(E[] elements,E target){
        elements[0] = target;
        int i = elements.length - 1;
        for(;!elements[i].equals(target);i--);
        return i;
    }

    /**
     * 不设置哨兵的顺序查找
     */
    public <E> int orderSearchWithoutSentinel(E[] elements,E target){
        for(int i=elements.length-1;i>0;i--){
            if(elements[i].equals(target)){
                return i;
            }
        }
        return 0;
    }

}
