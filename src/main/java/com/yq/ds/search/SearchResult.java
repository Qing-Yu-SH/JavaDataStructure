package com.yq.ds.search;

/**
 * @program: JavaDataStructure
 * @description: B-树查询结果类
 * @author: Yuqing
 * @create: 2023-06-20 09:16
 **/
public class SearchResult {

    // 关键字所在结点
    private final BNode node;

    // 是否存在
    private final boolean isExist;

    // 下标
    private final int index;

    public SearchResult(BNode node, boolean isExist, int index) {
        this.node = node;
        this.isExist = isExist;
        this.index = index;
    }

    public BNode getNode() {
        return node;
    }

    public boolean isExist() {
        return isExist;
    }

    public int getIndex() {
        return index;
    }
}
