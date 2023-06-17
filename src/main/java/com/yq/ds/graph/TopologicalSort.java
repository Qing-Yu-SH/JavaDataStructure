package com.yq.ds.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @program: JavaDataStructure
 * @description: 拓扑排序
 * @author: Yuqing
 * @create: 2023-06-17 23:15
 **/
public class TopologicalSort {

    public void topological_AM(AdjMatrix matrix){
        int vexNum = matrix.vexNum;
        // 保存拓扑排序
        List<String> topo = new ArrayList<>();
        // 存放各顶点入度
        int[] inDegree = new int[vexNum];
        // 计算各顶点入度
        for(int i=0;i<vexNum;i++){
            for(int j=0;j<vexNum;j++){
                if((matrix.hasWeight && matrix.arcs[i][j]!=Integer.MAX_VALUE) || (!matrix.hasWeight && matrix.arcs[i][j]!=0)){
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
            topo.add(matrix.vexs[v]);
            for(int j=0;j<vexNum;j++){
                if((matrix.hasWeight && matrix.arcs[v][j]!=Integer.MAX_VALUE) || (!matrix.hasWeight && matrix.arcs[v][j]!=0)){
                    inDegree[j]--;
                    if(inDegree[j] == 0){
                        stack.push(j);
                    }
                }
            }
        }

        if(topo.size() == vexNum){
            StringBuilder sb = new StringBuilder();
            for(String vex: topo){
                sb.append(vex).append(" ");
            }
            System.out.println(sb.toString());
        }else{
            System.out.println("AOV-网 存在环");
        }

    }

    public void topological_AL(AdjacencyList adjacencyList){
        int vexNum = adjacencyList.vexNum;
        // 保存拓扑排序
        List<String> topo = new ArrayList<>();
        // 存放各顶点入度
        int[] inDegree = new int[vexNum];
        // 计算各顶点入度
        for(int i=0;i<vexNum;i++){
            AdjacencyList.EdgeNode p = adjacencyList.nodes[i].firstArc;
            while (p != null){
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
            topo.add(adjacencyList.nodes[v].data);
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
            StringBuilder sb = new StringBuilder();
            for(String vex: topo){
                sb.append(vex).append(" ");
            }
            System.out.println(sb.toString());
        }else{
            System.out.println("AOV-网 存在环");
        }

    }
}
