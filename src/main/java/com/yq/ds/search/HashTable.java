package com.yq.ds.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: JavaDataStructure
 * @description: 散列表
 * @author: Yuqing
 * @create: 2023-06-20 13:42
 **/
public class HashTable<E extends Comparable> {

    private final E NULLKEY = null;
    private Object[] elements;
    private int length;

    public HashTable(int length) {
        this.length = length;
        this.elements =new Object[length];
    }

    /**
     * 散列函数
     */
    public int hash(E val){
        return val.hashCode() % length;
    }

    /**
     * 寻找空地址
     */
    private int getNULLAddress(E val){
        int index = hash(val);
        if(elements[index] == NULLKEY){
            return index;
        }else if(val.equals(elements[index])){
            return -1;
        }else{
            // 线性探测法
            for(int i=1;i<length;i++){
                int hi = (index+i) % length;
                if(elements[hi] == NULLKEY){
                    return hi;
                }
            }
        }
        return -1;
    }

    /**
     * 插入
     */
    public boolean insert(E val){
        int index = getNULLAddress(val);
        if(index == -1){
            return false;
        }
        elements[index] = val;
        return true;
    }

    /**
     * 搜索
     */
    public int select(E val){
        int h0 = hash(val);
        if(elements[h0] == NULLKEY){
            return -1;
        }else if(elements[h0].equals(val)){
            return h0;
        }else{
            for(int i=1;i<length;i++){
                int hi = (h0 + i) % length;
                if(elements[hi] == NULLKEY){
                    return -1;
                }else if(elements[hi].equals(val)){
                    return hi;
                }
            }
        }
        return -1;
    }

}
