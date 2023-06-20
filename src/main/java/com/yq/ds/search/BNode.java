package com.yq.ds.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @program: JavaDataStructure
 * @description: B-树节点
 * @author: Yuqing
 * @create: 2023-06-20 09:06
 **/
public class BNode<E extends Comparable> {

    // 关键字列表
    private final List<E> keyList;

    // 孩子结点列表
    private final List<BNode<E>> childNodesList;

    // 是否叶子结点
    private boolean isLeaf;

    // 双亲结点
    private BNode<E> parent;

    public BNode() {
        this.keyList = new ArrayList<>();
        this.childNodesList = new ArrayList<>();
        this.isLeaf = false;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public BNode<E> getParent() {
        return parent;
    }

    public void setParent(BNode<E> parent) {
        this.parent = parent;
    }

    public List<BNode<E>> getChildNodesList() {
        return childNodesList;
    }

    public List<E> getKeyList() {
        return keyList;
    }

    /**
     * 结点中关键字的个数
     */
    public int keySize() {
        return this.keyList.size();
    }

    /**
     * 采用二分查找法在结点内查找关键字
     */
    public SearchResult searchResult(E key){
        int left = 0,right = this.keySize()-1;
        boolean isExist = false;
        while (left <= right){
            int mid = left + (right-left)/2;
            E midValue = this.keyList.get(mid);
            int cmp = midValue.compareTo(key);
            if(cmp == 0){
                isExist = true;
                left = mid;
                break;
            }else{
                // midValue < key
                if(cmp < 0){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }
        if(isExist){
            return new SearchResult(this,isExist,left);
        }else{
            if(right == -1){
                // 小于列表中的所有结点
                return new SearchResult(this,false,0);
            }else if(left == this.keySize()){
                // 大于列表中的所有结点
                return new SearchResult(this,false,this.keySize());
            }
            // 例如 P0 1 P1 2 P2 6 P3 7 P4
            // 查 5，得到 left=2,right=1 ，应查找 P2
            return new SearchResult(this,false,left);
        }
    }

}
