package com.yq.ds.graph;

import java.util.Arrays;

/**
 * @program: JavaDataStructure
 * @description: 最短路径 - 弗洛伊德（Floyd）算法
 * @author: Yuqing
 * @create: 2023-06-17 10:11
 **/
public class Floyd {

    public void floyd_AM(AdjMatrix matrix){
        int vexNum = matrix.vexNum;
        // Path[i][j]：表明从顶点 i 到顶点 j 的路径上，顶点 j 的前一顶点的序号
        int[][] Path = new int[vexNum][vexNum];
        // D[i][j]：记录顶点 i 和 j 之间的最短路径长度
        int[][] D = new int[vexNum][vexNum];

        // 初始化
        for(int i=0;i<vexNum;i++){
            for(int j=0;j<vexNum;j++){
                D[i][j] = matrix.arcs[i][j];
                if(D[i][j] < Integer.MAX_VALUE){
                    // 如果 i 和 j 之间有弧，则将 j 的前驱置为 i
                    Path[i][j] = i;
                }else{
                    // 如果 i 和 j 之间无弧，则将 j 的前驱置为 -1
                    Path[i][j] = -1;
                }
            }
        }

        // 遍历中间节点
        for(int k=0;k<vexNum;k++){
            for(int i=0;i<vexNum;i++){
                for(int j=0;j<vexNum;j++){
                    // 从 i 经 k 到 j 的一条路径更短
                    if(D[i][k]!=Integer.MAX_VALUE && D[k][j]!=Integer.MAX_VALUE
                            && (D[i][k]+D[k][j] < D[i][j])){
                        D[i][j] = D[i][k] + D[k][j];
                        Path[i][j] = Path[k][j];
                    }
                }
            }
        }
        printPath(matrix,D,Path);
    }

    private void printPath(AdjMatrix matrix,int[][] D,int[][] path) {
        for (int i = 0; i < matrix.vexNum; i++) {
            for (int j = 0; j < matrix.vexNum; j++) {
                if (i != j) {
                    if (D[i][j] == Integer.MAX_VALUE) {
                        System.out.println("(" + matrix.vexs[i] + " " + matrix.vexs[j]
                                + ") -> No path");
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append(matrix.vexs[j]).append(")");
                        int pre = path[i][j];
                        while (pre != i) {
                            sb.insert(0, matrix.vexs[pre] + ",");
                            pre = path[i][pre];
                        }
                        sb.insert(0, "(" + matrix.vexs[i] + ",");
                        sb.append(" -> ").append(D[i][j]);
                        System.out.println(sb.toString());
                    }
                }
            }
        }
    }

    public void floyd_AL(AdjacencyList adjacencyList){
        int vexNum = adjacencyList.vexNum;
        // Path[i][j]：表明从顶点 i 到顶点 j 的路径上，顶点 j 的前一顶点的序号
        int[][] Path = new int[vexNum][vexNum];
        // D[i][j]：记录顶点 i 和 j 之间的最短路径长度
        int[][] D = new int[vexNum][vexNum];

        // 初始化
        for(int i=0;i<vexNum;i++){
            Arrays.fill(Path[i],-1);
            Arrays.fill(D[i],Integer.MAX_VALUE);

            AdjacencyList.EdgeNode p = adjacencyList.nodes[i].firstArc;
            while (p != null){
                int next = p.adjVex;
                Path[i][next] = i;
                D[i][next] = p.w;
                p = p.nextArc;
            }
        }

        // 遍历中间节点
        for(int k=0;k<vexNum;k++){
            for(int i=0;i<vexNum;i++){
                for(int j=0;j<vexNum;j++){
                    // 从 i 经 k 到 j 的一条路径更短
                    if(D[i][k]!=Integer.MAX_VALUE && D[k][j]!=Integer.MAX_VALUE
                            && (D[i][k]+D[k][j] < D[i][j])){
                        D[i][j] = D[i][k] + D[k][j];
                        Path[i][j] = Path[k][j];
                    }
                }
            }
        }
        printPath(adjacencyList,D,Path);
    }

    private void printPath(AdjacencyList adjacencyList,int[][] D,int[][] path) {
        for (int i = 0; i < adjacencyList.vexNum; i++) {
            for (int j = 0; j < adjacencyList.vexNum; j++) {
                if (i != j) {
                    if (D[i][j] == Integer.MAX_VALUE) {
                        System.out.println("(" + adjacencyList.nodes[i].data + " " + adjacencyList.nodes[j].data
                                + ") -> No path");
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append(adjacencyList.nodes[j].data).append(")");
                        int pre = path[i][j];
                        while (pre != i) {
                            sb.insert(0, adjacencyList.nodes[pre].data + ",");
                            pre = path[i][pre];
                        }
                        sb.insert(0, "(" + adjacencyList.nodes[i].data + ",");
                        sb.append(" -> ").append(D[i][j]);
                        System.out.println(sb.toString());
                    }
                }
            }
        }
    }

}
