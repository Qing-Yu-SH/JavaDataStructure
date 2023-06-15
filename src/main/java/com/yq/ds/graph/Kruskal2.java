package com.yq.ds.graph;

import java.util.PriorityQueue;

/**
 * @program: JavaDataStructure
 * @description: 通过 Union-Find 实现 Kruskal
 * @author: Yuqing
 * @create: 2023-06-15 22:11
 **/
public class Kruskal2 {

    public int kruskal_AM(AdjMatrix matrix){
        int vexNum = matrix.vexNum;
        UF uf = new UF(vexNum);
        int mst = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2]-b[2]);
        for(int i=0;i<vexNum;i++){
            for(int j=0;j<vexNum;j++){
                if(matrix.arcs[i][j] != Integer.MAX_VALUE){
                    pq.offer(new int[]{i,j,matrix.arcs[i][j]});
                }
            }
        }

        while(!pq.isEmpty()){
            int[] edge = pq.poll();
            int u = edge[0];
            int v = edge[1];
            if(uf.connected(u,v)){
                continue;
            }
            System.out.println("(" + matrix.vexs[u] + "," + matrix.vexs[v] + ") -> " + edge[2]);
            mst += edge[2];
            uf.union(u,v);
        }

        return uf.count==1 ? mst:-1;
    }

    public int kruskal_AL(AdjacencyList adjacencyList){
        int vexNum = adjacencyList.vexNum;
        UF uf = new UF(vexNum);
        int mst = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2]-b[2]);
        for(int i=0;i<vexNum;i++){
            AdjacencyList.EdgeNode edgeNode = adjacencyList.nodes[i].firstArc;
            while(edgeNode != null){
                pq.offer(new int[]{i, edgeNode.adjVex, edgeNode.w});
                edgeNode = edgeNode.nextArc;
            }
        }

        while(!pq.isEmpty()){
            int[] edge = pq.poll();
            int u = edge[0];
            int v = edge[1];
            if(uf.connected(u,v)){
                continue;
            }
            System.out.println("(" + adjacencyList.nodes[u].data + "," + adjacencyList.nodes[v].data + ") -> " + edge[2]);
            mst += edge[2];
            uf.union(u,v);
        }

        return uf.count==1 ? mst:-1;
    }

    private static class UF{
        private int count;
        private int[] parent;

        public UF(int n){
            parent = new int[n];
            for(int i=0;i<n;i++){
                parent[i] = i;
            }
            this.count = n;
        }

        public void union(int p,int q){
            int rootP = find(p);
            int rootQ = find(q);
            if(rootP == rootQ){
                return;
            }
            parent[rootP] = rootQ;
            count--;
        }

        public boolean connected(int p,int q){
            int rootP = find(p);
            int rootQ = find(q);
            return rootP == rootQ;
        }

        public int find(int x){
            while(parent[x] != x){
                parent[x] = find(parent[x]);
                x = parent[x];
            }
            return x;
        }

        public int count(){
            return count;
        }
    }
}
