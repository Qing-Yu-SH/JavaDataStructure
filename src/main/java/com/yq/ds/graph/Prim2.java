package com.yq.ds.graph;

import java.util.List;
import java.util.PriorityQueue;

/**
 * @program: JavaDataStructure
 * @description: 通过 BFS 实现 Prim 算法
 * @author: Yuqing
 * @create: 2023-06-15 16:33
 **/
public class Prim2 {

    // 核心数据结构，存储「横切边」的优先级队列
    private PriorityQueue<int[]> pq;
    // 类似 visited 数组的作用，记录哪些节点已经成为最小生成树的一部分
    boolean[] inMST;
    // 权值总和
    private int weightSum;
    // 边集合：edge[0] - 起点；edge[1] - 终点；edge[2] - 权值
    private List<int[]>[] graph;

    public void prim_AM(AdjMatrix matrix){
        int vexNum = matrix.vexNum;
        boolean[] visited = new boolean[vexNum];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2]-b[2]);
        int k = 0;
        visited[0] = true;
        for(int i=0;i<vexNum;i++){
            if(matrix.arcs[k][i] != Integer.MAX_VALUE){
                // 边：edge[0] - 起点；edge[1] - 终点；edge[2] - 权值
                pq.offer(new int[]{k,i,matrix.arcs[k][i]});
            }
        }
        while (!pq.isEmpty()){
            int[] edge = pq.poll();
            int from = edge[0];
            int to = edge[1];
            int w = edge[2];
            if(visited[to]){
                continue;
            }
            visited[to] = true;
            System.out.println("(" + matrix.vexs[from] + "," + matrix.vexs[to] + ") -> " + w);
            for(int i=0;i<matrix.vexNum;i++){
                if(!visited[i] && matrix.arcs[to][i]!=Integer.MAX_VALUE){
                    pq.offer(new int[]{to,i,matrix.arcs[to][i]});
                }
            }
        }
    }

    public void prim_AL(AdjacencyList adjacencyList){
        int vexNum = adjacencyList.vexNum;
        boolean[] visited = new boolean[vexNum];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2]-b[2]);
        int k = 0;
        visited[0] = true;

        AdjacencyList.EdgeNode p = adjacencyList.nodes[k].firstArc;
        while(p != null){
            pq.offer(new int[]{k,p.adjVex,p.w});
            p = p.nextArc;
        }

        while (!pq.isEmpty()){
            int[] edge = pq.poll();
            int from = edge[0];
            int to = edge[1];
            int w = edge[2];
            if(visited[to]){
                continue;
            }
            visited[to] = true;
            System.out.println("(" + adjacencyList.nodes[from].data + "," + adjacencyList.nodes[to].data + ") -> " + w);
            p = adjacencyList.nodes[to].firstArc;
            while(p != null){
                pq.offer(new int[]{to,p.adjVex,p.w});
                p = p.nextArc;
            }
        }
    }

}