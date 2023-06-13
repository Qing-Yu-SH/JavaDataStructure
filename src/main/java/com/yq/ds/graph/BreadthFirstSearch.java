package com.yq.ds.graph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @program: JavaDataStructure
 * @description: 广度优先搜索
 * @author: Yuqing
 * @create: 2023-06-13 19:59
 **/
public class BreadthFirstSearch {

    /**
     * 邻接矩阵 - 广度优先搜索
     * @param matrix
     */
    public static void BFS(AdjMatrix matrix){
        boolean[] visited = new boolean[matrix.vexNum];
        for(int i=0;i < matrix.vexNum;i++){
            if(!visited[i]){
                bfs_AM(matrix, i, visited);
            }
        }
        System.out.println();
    }

    private static void bfs_AM(AdjMatrix matrix,int v,boolean[] visited){
        // 访问该结点
        System.out.print(matrix.vexs[v] + " ");
        visited[v] = true;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(v);
        boolean hasWeight = matrix.hasWeight;
        while (!queue.isEmpty()){
            int v2 = queue.poll();
            for(int w=0;w < matrix.vexNum;w++){
                if(!visited[w] && ((hasWeight&&matrix.arcs[v2][w]!=Integer.MAX_VALUE) || (!hasWeight&&matrix.arcs[v2][w]!=0))){
                    System.out.print(matrix.vexs[w] + " ");
                    visited[w] = true;
                    queue.offer(w);
                }
            }
        }
    }

    /**
     * 邻接表 - 广度优先搜索
     * @param list
     */
    public static void BFS(AdjacencyList list){
        boolean[] visited = new boolean[list.vexNum];
        for(int i=0;i < list.vexNum;i++){
            if(!visited[i]){
                bfs_AL(list, i, visited);
            }
        }
        System.out.println();
    }

    private static void bfs_AL(AdjacencyList list,int v,boolean[] visited){
        // 访问该结点
        System.out.print(list.nodes[v].data + " ");
        visited[v] = true;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(v);
        while (!queue.isEmpty()){
            int v2 = queue.poll();
            AdjacencyList.EdgeNode edgeNode = list.nodes[v2].firstArc;
            while (edgeNode != null){
                int w = edgeNode.adjVex;
                if(!visited[w]){
                    System.out.print(list.nodes[w].data + " ");
                    visited[w] = true;
                    queue.offer(w);
                }
                edgeNode = edgeNode.nextArc;
            }
        }
    }
}
