package com.yq.ds.graph;

/**
 * @program: JavaDataStructure
 * @description: 最小生成树 - 普利姆算法(Prim算法)
 * @author: Yuqing
 * @create: 2023-06-14 20:06
 **/
public class Prim {

    public void prim_AM(AdjMatrix matrix){
        int vexNum = matrix.vexNum;
        CloseEdge[] closeEdges = new CloseEdge[vexNum];
        int k = 0;
        // 初始化边集合；Integer.MAX_VALUE 表示未连接；0 表示已访问
        for(int j=0;j<vexNum;j++){
            closeEdges[j] = new CloseEdge();
            closeEdges[j].adjVex = k;
            closeEdges[j].lowCost = matrix.arcs[k][j];
        }

        // 初始：U = {k}
        closeEdges[k].lowCost = 0;
        for(int i=1;i<vexNum;i++){
            // 从 V-U 中找到一个结点和 U 某个结点的边的权值最小
            k = getMinAdjVex(closeEdges);
            // u 为最小边的一个顶点，u 属于 U
            int u = closeEdges[k].adjVex;
            // v 为最小边的另一个顶点，v 属于 V-U
            int v = k;

            System.out.println("(" + matrix.vexs[u] + "," + matrix.vexs[v] + ") -> " + matrix.arcs[u][v]);

            // 将顶点 v 并入集合 U
            closeEdges[v].lowCost = 0;
            // 加入 v 后，更新边集合
            for(int j=0;j<vexNum;j++){
                if(matrix.arcs[v][j]!=Integer.MAX_VALUE && matrix.arcs[v][j]<closeEdges[j].lowCost){
                    closeEdges[j].adjVex = v;
                    closeEdges[j].lowCost = matrix.arcs[v][j];
                }
            }
        }
    }


    public void prim_AL(AdjacencyList adjacencyList){
        int vexNum = adjacencyList.vexNum;
        int k = 0;
        CloseEdge[] closeEdges = new CloseEdge[vexNum];
        // 初始化，并将与顶点 k 相连的边存入 closeEdge 集合
        AdjacencyList.EdgeNode p = adjacencyList.nodes[k].firstArc;
        while (p != null){
            closeEdges[p.adjVex] = new CloseEdge();
            closeEdges[p.adjVex].adjVex = k;
            closeEdges[p.adjVex].lowCost = p.w;
            p = p.nextArc;
        }
        for(int j=0;j<vexNum;j++){
            if(closeEdges[j] == null){
                closeEdges[j] = new CloseEdge();
            }
        }
        // 初始，U={k}
        closeEdges[k].lowCost = 0;
        for(int i=1;i<vexNum;i++){
            k = getMinAdjVex(closeEdges);

            // u 为最小边的一个顶点，u 属于 U
            int u = closeEdges[k].adjVex;
            // v 为最小边的另一个顶点，v 属于 V-U
            int v = k;

            System.out.println("(" + adjacencyList.nodes[u].data + "," + adjacencyList.nodes[v].data + ") -> " + closeEdges[v].lowCost);

            // 将顶点 v0 并入集合 U
            closeEdges[v].lowCost = 0;
            // 重新选择最小边
            p = adjacencyList.nodes[v].firstArc;
            while(p != null){
                if(p.w < closeEdges[p.adjVex].lowCost){
                    closeEdges[p.adjVex].adjVex = v;
                    closeEdges[p.adjVex].lowCost = p.w;
                }
                p = p.nextArc;
            }
        }
    }

    /**
     * 求出最小边点的索引位置
     */
    private int getMinAdjVex(CloseEdge[] closeEdges){
        int minAdjVex = -1;
        for(int i=0;i< closeEdges.length;i++){
            if(closeEdges[i].lowCost != 0){
                if(minAdjVex == -1){
                    minAdjVex = i;
                }else{
                    minAdjVex = closeEdges[minAdjVex].lowCost < closeEdges[i].lowCost ? minAdjVex:i;
                }
            }
        }
        return minAdjVex;
    }


    class CloseEdge{
        // 最小边在 U 中的那个顶点，起始点
        int adjVex;
        // 最小边上的权值
        int lowCost;

        public CloseEdge() {
            this.adjVex = -1;
            this.lowCost = Integer.MAX_VALUE;
        }
    }
}
