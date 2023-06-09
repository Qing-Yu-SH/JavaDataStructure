package com.yq.ds.graph;

/**
 * @program: JavaDataStructure
 * @description: 邻接矩阵
 * @author: Yuqing
 * @create: 2023-06-09 21:53
 **/
public class AdjMatrix {

    // 顶点
    String[] vexs;
    // 邻接矩阵
    int[][] arcs;
    int vexNum,arcNum;

    public AdjMatrix(int vexNum, int arcNum) {
        this.vexNum = vexNum;
        this.arcNum = arcNum;
        vexs = new String[vexNum];
        arcs = new int[vexNum][vexNum];
    }
}
