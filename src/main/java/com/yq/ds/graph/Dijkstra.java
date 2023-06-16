package com.yq.ds.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: JavaDataStructure
 * @description: 最短路径 - 迪杰斯特拉（Dijkstra）算法
 * @author: Yuqing
 * @create: 2023-06-16 22:53
 **/
public class Dijkstra {

    public void dijkstra_AM(AdjMatrix matrix,int v0){
        int vexNum = matrix.vexNum;
        // 记录从源点 v0 到终点 vi 是否已被确定最短路径长度
        boolean[] S = new boolean[vexNum];
        // Path[i]：记录从源点 v0 到终点 vi 的当前最短路径上 vi 的直接前驱顶点序号
        // 其初值为：如果从 v0 到 vi 有弧，则 Path[i] 为 v0，否则为 -1
        int[] Path = new int[vexNum];
        Arrays.fill(Path,-1);
        // D[i]：记录从源点 v0 到终点 vi 的当前最短路径长度
        // 其初值为：如果从 v0 到 vi 有弧，则 D[i] 为弧上的权值，否则为 ∞
        int[] D = new int[vexNum];

        // 初始化
        for(int v=0;v<vexNum;v++){
            S[v] = false;
            D[v] = matrix.arcs[v0][v];
            if(D[v] < Integer.MAX_VALUE){
                Path[v] = v0;
            }
        }

        S[v0] = true;
        D[v0] = 0;

        for(int i=1;i<vexNum;i++){
            int v = v0;
            int minArc = Integer.MAX_VALUE;

            // 选择一条当前最短路径，终点为 v
            for(int u=0;u<vexNum;u++){
                if(!S[u] && D[u]<minArc){
                    v = u;
                    minArc = D[u];
                }
            }

            S[v] = true;
            printPath(matrix,Path,v0,minArc,v);
            // 更新从 v 出发到集合 V-S 上所有顶点的最短路径长度
            for(int u=0;u<vexNum;u++){
                if(!S[u] && matrix.arcs[v][u]!=Integer.MAX_VALUE && D[v]+matrix.arcs[v][u]<D[u]){
                    D[u] = D[v]+matrix.arcs[v][u];
                    Path[u] = v;
                }
            }
        }
    }

    private void printPath(AdjMatrix matrix,int[] path,int v0,int weight,int v){
        if(v != v0){
            StringBuilder builder = new StringBuilder();
            builder.append(matrix.vexs[v]).append(")");
            v = path[v];
            while(v != -1){
                builder.insert(0,matrix.vexs[v]+",");
                v = path[v];
            }
            builder.insert(0,"(");
            builder.append(" -> ").append(weight);
            System.out.println(builder.toString());
        }
    }

    public void dijkstra_AL(AdjacencyList adjacencyList,int v0){
        int vexNum = adjacencyList.vexNum;
        boolean[] S = new boolean[vexNum];
        int[] Path = new int[vexNum];
        Arrays.fill(Path,-1);
        int[] D = new int[vexNum];
        Arrays.fill(D,Integer.MAX_VALUE);
        // 保存每个结点的边表情况
        Map<Integer,Integer>[] vexMap = new Map[vexNum];
        for(int i=0;i<vexNum;i++){
            vexMap[i] = new HashMap<Integer,Integer>();
            AdjacencyList.EdgeNode p = adjacencyList.nodes[i].firstArc;
            while(p != null){
                vexMap[i].put(p.adjVex, p.w);
                p = p.nextArc;
            }
        }

        S[v0] = true;
        D[v0] = 0;
        for(Integer u: vexMap[v0].keySet()){
            Path[u] = v0;
            D[u] = vexMap[v0].get(u);
        }

        for(int i=1;i<vexNum;i++){
            int v = v0;
            int minArc = Integer.MAX_VALUE;

            for(int u=0;u<vexNum;u++){
                if(!S[u] && D[u]<minArc){
                    v = u;
                    minArc = D[u];
                }
            }

            S[v] = true;
            printPath(adjacencyList,Path,v0,D[v],v);
            for(int u=0;u<vexNum;u++){
                if(!S[u] && vexMap[v].containsKey(u) && D[v]+vexMap[v].get(u)<D[u]){
                    D[u] = D[v]+vexMap[v].get(u);
                    Path[u] = v;
                }
            }
        }
    }

    private void printPath(AdjacencyList adjacencyList,int[] path,int v0,int weight,int v){
        if(v != v0){
            StringBuilder builder = new StringBuilder();
            builder.append(adjacencyList.nodes[v].data).append(")");
            v = path[v];
            while(v != -1){
                builder.insert(0,adjacencyList.nodes[v].data + ",");
                v = path[v];
            }
            builder.insert(0,"(");
            builder.append(" -> ").append(weight);
            System.out.println(builder.toString());
        }
    }
}
