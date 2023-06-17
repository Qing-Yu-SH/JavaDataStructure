package com.yq.ds.graph;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @program: JavaDataStructure
 * @description: 通过优先队列实现 Dijkstra 算法
 * @author: Yuqing
 * @create: 2023-06-17 09:28
 **/
public class Dijkstra2 {

    public void dijkstra_AM(AdjMatrix matrix,int start){
        int vexNum = matrix.vexNum;
        // 记录最短路径的权重
        // 定义：distTo[i] 的值就是节点 start 到达节点 i 的最短路径权重
        int[] distTo = new int[vexNum];
        Arrays.fill(distTo,Integer.MAX_VALUE);
        distTo[start] = 0;
        // 记录每个节点在最短路径中的前驱节点
        int[] path = new int[vexNum];
        Arrays.fill(path,-1);
        // 优先队列记录每条当前的最短路径
        PriorityQueue<State> pq = new PriorityQueue<>((a,b) -> a.distFromStart-b.distFromStart);
        pq.offer(new State(start,0));

        while (!pq.isEmpty()){
            State state = pq.poll();
            int curId = state.id;
            int curDistFromStart = state.distFromStart;

            // 当前状态的从源点到 curId 的路径大于 distTo 中记录的目前最短路径，说明这条路径不是最优的，跳过
            if(curDistFromStart > distTo[curId]){
                continue;
            }

            // 从 curId 出发，更新从源点到各个顶点的最短路径
            for(int v=0;v<vexNum;v++){
                if(matrix.arcs[curId][v]!=Integer.MAX_VALUE && curDistFromStart+matrix.arcs[curId][v]<distTo[v]){
                    path[v] = curId;
                    distTo[v] = curDistFromStart+matrix.arcs[curId][v];
                    pq.offer(new State(v,distTo[v]));
                }
            }
        }

        printPath(matrix,path,distTo,start);
    }

    private void printPath(AdjMatrix matrix,int[] path,int[] distTo,int start){
        for(int u=0;u< matrix.vexNum;u++){
            if(u!=start && distTo[u]!=Integer.MAX_VALUE){
                StringBuilder builder = new StringBuilder();
                builder.append(matrix.vexs[u]).append(")");
                int weight = distTo[u];
                int next = path[u];
                while(next != -1){
                    builder.insert(0,matrix.vexs[next]+",");
                    next = path[next];
                }
                builder.insert(0,"(");
                builder.append(" -> ").append(weight);
                System.out.println(builder.toString());
            }else if(distTo[u] == Integer.MAX_VALUE){
                System.out.println(matrix.vexs[start] + " -> " + matrix.vexs[u] + "：不可达");
            }
        }
    }

    public void dijkstra_AL(AdjacencyList adjacencyList,int start){
        int vexNum = adjacencyList.vexNum;
        int[] distTo = new int[vexNum];
        Arrays.fill(distTo,Integer.MAX_VALUE);
        distTo[start] = 0;
        int[] path = new int[vexNum];
        Arrays.fill(path,-1);
        PriorityQueue<State> pq = new PriorityQueue<>((a,b) -> a.distFromStart-b.distFromStart);
        pq.offer(new State(start,0));

        while(!pq.isEmpty()){
            State state = pq.poll();
            int curId = state.id;
            int curDistFromStart = state.distFromStart;

            if(curDistFromStart > distTo[curId]){
                continue;
            }

            AdjacencyList.EdgeNode p = adjacencyList.nodes[curId].firstArc;
            while (p != null){
                int nextNodeId = p.adjVex;
                int distToNextNode = curDistFromStart + p.w;
                if(distToNextNode < distTo[nextNodeId]){
                    distTo[nextNodeId] = distToNextNode;
                    path[nextNodeId] = curId;
                    pq.offer(new State(nextNodeId,distToNextNode));
                }
                p = p.nextArc;
            }
        }

        printPath(adjacencyList,path,distTo,start);
    }

    private void printPath(AdjacencyList adjacencyList,int[] path,int[] distTo,int start){
        for(int u=0;u<adjacencyList.vexNum;u++){
            if(u!=start && distTo[u]!=Integer.MAX_VALUE){
                StringBuilder builder = new StringBuilder();
                builder.append(adjacencyList.nodes[u].data).append(")");
                int weight = distTo[u];
                int next = path[u];
                while(next != -1){
                    builder.insert(0,adjacencyList.nodes[next].data + ",");
                    next = path[next];
                }
                builder.insert(0,"(");
                builder.append(" -> ").append(weight);
                System.out.println(builder.toString());
            }else if(distTo[u] == Integer.MAX_VALUE){
                System.out.println(adjacencyList.nodes[start].data + " -> " + adjacencyList.nodes[u].data + "：不可达");
            }
        }
    }



    class State{
        // 图节点的 id
        int id;
        // 从 start 节点到当前节点的距离
        int distFromStart;

        State(int id, int distFromStart) {
            this.id = id;
            this.distFromStart = distFromStart;
        }
    }

}
