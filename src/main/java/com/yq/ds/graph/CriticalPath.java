package com.yq.ds.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @program: JavaDataStructure
 * @description: 关键路径
 * @author: Yuqing
 * @create: 2023-06-18 12:04
 **/
public class CriticalPath {


    public void criticalPath_AM(AdjMatrix matrix){
        List<Integer> topo = new ArrayList<>();
        if(!TopologicalSort(matrix,topo)){
            System.out.println("AOE-网 存在环");
            return;
        }

        int n = matrix.vexNum;
        // 保存每个事件的最早发生时间
        int[] ve = new int[n];
        // 保存每个事件的最迟发生时间
        int[] vl = new int[n];


        // 求每个事件的最早发生时间
        for(int i=0;i<n;i++){
            int k = topo.get(i);

            for(int j=0;j<n;j++){
                if(j!=k && matrix.arcs[k][j]!=Integer.MAX_VALUE){
                    ve[j] = Math.max(ve[j],ve[k]+matrix.arcs[k][j]);
                }
            }
        }

        // 给每个事件的最迟发生时间值初值 ve[n-1]
        Arrays.fill(vl,ve[n-1]);
        // 求每个事件的最迟发生时间
        for(int i=n-1;i>=0;i--){
            int k = topo.get(i);

            for(int j=0;j<n;j++){
                if(j!=k && matrix.arcs[k][j]!=Integer.MAX_VALUE){
                    vl[k] = Math.min(vl[k],vl[j]-matrix.arcs[k][j]);
                }
            }

        }

        // 计算关键活动，每次循环针对 vi 为活动开始点的所有活动
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++){
                if(i!=j && matrix.arcs[i][j]!=Integer.MAX_VALUE){
                    // 计算活动 <vi,vj> 的最早开始时间
                    int e = ve[i];
                    // 计算活动 <vi,vj> 的最迟开始时间
                    int l = vl[j] - matrix.arcs[i][j];
                    if(e == l){
                        System.out.println("< " + matrix.vexs[i] + " , "
                                + matrix.vexs[j] + " >");
                    }
                }
            }
        }

    }


    /**
     * AOV-网 拓扑排序
     */
    private boolean TopologicalSort(AdjMatrix matrix, List<Integer> topo){
        int vexNum = matrix.vexNum;
        // 存放各顶点入度
        int[] inDegree = new int[vexNum];
        // 计算各顶点入度
        for(int i=0;i<vexNum;i++){
            for(int j=0;j<vexNum;j++){
                if(matrix.arcs[i][j]!=Integer.MAX_VALUE){
                    inDegree[j]++;
                }
            }
        }

        // 暂存所有入度为 0 的顶点
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<vexNum;i++){
            if(inDegree[i] == 0){
                stack.push(i);
            }
        }

        while (!stack.isEmpty()){
            int v = stack.pop();
            topo.add(v);
            for(int j=0;j<vexNum;j++){
                if(matrix.arcs[v][j]!=Integer.MAX_VALUE){
                    inDegree[j]--;
                    if(inDegree[j] == 0){
                        stack.push(j);
                    }
                }
            }
        }

        if(topo.size() == vexNum){
            return true;
        }else{
            return false;
        }
    }


    public void criticalPath_AL(AdjacencyList adjacencyList){
        List<Integer> topo = new ArrayList<>();
        if(!TopologicalSort(adjacencyList,topo)){
            System.out.println("AOE-网 存在环");
            return;
        }

        int n = adjacencyList.vexNum;
        // 保存每个事件的最早发生时间
        int[] ve = new int[n];
        // 保存每个事件的最迟发生时间
        int[] vl = new int[n];


        // 求每个事件的最早发生时间
        for(int i=0;i<n;i++){
            int k = topo.get(i);

            AdjacencyList.EdgeNode p = adjacencyList.nodes[k].firstArc;
            while(p != null){
                ve[p.adjVex] = Math.max(ve[p.adjVex],ve[k]+p.w);
                p = p.nextArc;
            }
        }

        // 给每个事件的最迟发生时间值初值 ve[n-1]
        Arrays.fill(vl,ve[n-1]);
        // 求每个事件的最迟发生时间
        for(int i=n-1;i>=0;i--){
            int k = topo.get(i);

            AdjacencyList.EdgeNode p = adjacencyList.nodes[k].firstArc;
            while(p != null){
                vl[k] = Math.min(vl[k],vl[p.adjVex]-p.w);
                p = p.nextArc;
            }

        }

        // 计算关键活动，每次循环针对 vi 为活动开始点的所有活动
        for(int i=0;i<n;i++) {
            AdjacencyList.EdgeNode p = adjacencyList.nodes[i].firstArc;
            while(p != null){
                int j = p.adjVex;
                // 计算活动 <vi,vj> 的最早开始时间
                int e = ve[i];
                // 计算活动 <vi,vj> 的最迟开始时间
                int l = vl[j] - p.w;
                if(e == l){
                    System.out.println("< " + adjacencyList.nodes[i].data + " , "
                            + adjacencyList.nodes[j].data + " >");
                }
                p = p.nextArc;
            }
        }
    }


    /**
     * AOV-网 拓扑排序
     */
    private boolean TopologicalSort(AdjacencyList adjacencyList, List<Integer> topo){
        int vexNum = adjacencyList.vexNum;
        // 存放各顶点入度
        int[] inDegree = new int[vexNum];
        // 计算各顶点入度
        for(int i=0;i<vexNum;i++){
            AdjacencyList.EdgeNode p = adjacencyList.nodes[i].firstArc;
            while(p != null){
                inDegree[p.adjVex]++;
                p = p.nextArc;
            }
        }

        // 暂存所有入度为 0 的顶点
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<vexNum;i++){
            if(inDegree[i] == 0){
                stack.push(i);
            }
        }

        while (!stack.isEmpty()){
            int v = stack.pop();
            topo.add(v);
            AdjacencyList.EdgeNode p = adjacencyList.nodes[v].firstArc;
            while(p != null){
                inDegree[p.adjVex]--;
                if(inDegree[p.adjVex] == 0){
                    stack.push(p.adjVex);
                }
                p = p.nextArc;
            }
        }

        if(topo.size() == vexNum){
            return true;
        }else{
            return false;
        }
    }

}
