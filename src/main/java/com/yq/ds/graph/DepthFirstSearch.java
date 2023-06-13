package com.yq.ds.graph;

/**
 * @program: JavaDataStructure
 * @description: 深度优先遍历
 * @author: Yuqing
 * @create: 2023-06-13 19:32
 **/
public class DepthFirstSearch {

    /**
     * 邻接矩阵 - 深度优先遍历
     * @param matrix 邻接矩阵
     */
    public static void DFS(AdjMatrix matrix){
        boolean[] visited = new boolean[matrix.vexNum];
        for(int i=0;i< matrix.vexNum;i++){
            if(!visited[i]){
                dfs_AM(matrix,i,visited);
            }
        }
        System.out.println();
    }

    private static void dfs_AM(AdjMatrix matrix,int v,boolean[] visited){
        // 访问该结点
        System.out.print(matrix.vexs[v] + " ");
        visited[v] = true;
        for(int w=0;w < matrix.vexNum;w++){
            boolean hasWeight = matrix.hasWeight;
            if(!visited[w] && ((hasWeight&&matrix.arcs[v][w]!=Integer.MAX_VALUE) || (!hasWeight&&matrix.arcs[v][w]!=0))){
                dfs_AM(matrix, w, visited);
            }
        }
    }

    /**
     * 邻接表 - 深度优先遍历
     * @param list 邻接表
     */
    public static void DFS(AdjacencyList list){
        boolean[] visited = new boolean[list.vexNum];
        for(int i=0;i<list.vexNum;i++){
            if(!visited[i]){
                dfs_AL(list,i,visited);
            }
        }
        System.out.println();
    }

    private static void dfs_AL(AdjacencyList list,int v,boolean[] visited){
        // 访问该结点
        System.out.print(list.nodes[v].data + " ");
        visited[v] = true;
        AdjacencyList.EdgeNode edgeNode = list.nodes[v].firstArc;
        while (edgeNode != null){
            int w = edgeNode.adjVex;
            if(!visited[w]){
                dfs_AL(list, w, visited);
            }
            edgeNode = edgeNode.nextArc;
        }
    }
}
