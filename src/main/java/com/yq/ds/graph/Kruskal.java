package com.yq.ds.graph;

import java.util.PriorityQueue;

/**
 * @program: JavaDataStructure
 * @description: 最小生成树  - Kruskal算法(克鲁斯卡尔算法)
 * @author: Yuqing
 * @create: 2023-06-15 19:39
 **/
public class Kruskal {


    public void kruskal_AM(AdjMatrix matrix){
        int vexNum = matrix.vexNum;
        // 保存连通分量，vexSet[i] = j 表示顶点 i 属于连通分量 j
        int[] vexSet = new int[vexNum];
        // 保存每条边的始点、终点和权值
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2]-b[2]);
        // 初始化
        for(int i=0;i<vexNum;i++){
            for(int j=0;j<vexNum;j++){
                if(matrix.arcs[i][j] != Integer.MAX_VALUE){
                    int[] edge = new int[3];
                    edge[0] = i;
                    edge[1] = j;
                    edge[2] = matrix.arcs[i][j];
                    pq.offer(edge);
                }
            }
        }

        // 各顶点自成一个连通分量
        for(int i=0;i<vexNum;i++){
            vexSet[i] = i;
        }

        while (!pq.isEmpty()){
            int[] edge = pq.poll();
            int u = edge[0];
            int v = edge[1];
            int vs1 = vexSet[u];
            int vs2 = vexSet[v];

            // 边的两个顶点分属不同的连通分量
            if(vs1 != vs2){
                System.out.println("(" + matrix.vexs[u] + ","
                        + matrix.vexs[v] + ") -> " + matrix.arcs[u][v]);
                // 合并 vs1 和 vs2 两个连通分量
                for(int j=0;j<vexNum;j++){
                    if(vexSet[j] == vs2){
                        vexSet[j] = vs1;
                    }
                }
            }

        }

    }

    public void kruskal_AL(AdjacencyList adjacencyList){
        int vexNum = adjacencyList.vexNum;
        // 保存连通分量，vexSet[i] = j 表示顶点 i 属于连通分量 j
        int[] vexSet = new int[vexNum];
        // 保存每条边的始点、终点和权值
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2]-b[2]);
        // 初始化
        for(int i=0;i<vexNum;i++){
            AdjacencyList.EdgeNode edgeNode = adjacencyList.nodes[i].firstArc;
            while(edgeNode != null){
                pq.offer(new int[]{i, edgeNode.adjVex, edgeNode.w});
                edgeNode = edgeNode.nextArc;
            }
        }

        // 各顶点自成一个连通分量
        for(int i=0;i<vexNum;i++){
            vexSet[i] = i;
        }

        while(!pq.isEmpty()){
            int[] edge = pq.poll();
            int u = edge[0];
            int v = edge[1];
            int vs1 = vexSet[u];
            int vs2 = vexSet[v];

            // 边的两个顶点分属不同的连通分量
            if(vs1 != vs2){
                System.out.println("(" + adjacencyList.nodes[u].data + ","
                        + adjacencyList.nodes[v].data + ") -> " + edge[2]);
                // 合并 vs1 和 vs2 两个连通分量
                for(int j=0;j<vexNum;j++){
                    if(vexSet[j] == vs2){
                        vexSet[j] = vs1;
                    }
                }
            }
        }
    }


    class Edge{
        // 边的始点
        int head;
        // 边的终点
        int tail;
        // 边上的权值
        int lowCost;
    }
}
