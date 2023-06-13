package com.yq.ds.graph;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: JavaDataStructure
 * @description: 邻接矩阵构建类
 * @author: Yuqing
 * @create: 2023-06-09 21:57
 **/
public class ConstructAdjMatrix {

    public static final int UDN_CODE = 0;
    public static final int DAG_CODE = 1;

    /**
     * 创建邻接矩阵
     * @param code 无向图 or 有向图
     * @param hasWeight 是否有权值
     * @return 邻接矩阵
     */
    public AdjMatrix createMatrix(int code,boolean hasWeight){
        assert code==0 || code==1;
        Scanner input=new Scanner(System.in);
        System.out.print("请输入顶点数：");
        int vexNum = input.nextInt();
        System.out.print("请输入边数：");
        int arcNum = input.nextInt();
        AdjMatrix graph = new AdjMatrix(vexNum, arcNum);
        // 有权值的情况，矩阵初始化为 Integer.MAX_VALUE，否则为 0
        if(hasWeight){
            for(int i=0;i < graph.vexNum;i++){
                Arrays.fill(graph.arcs[i],Integer.MAX_VALUE);
            }
        }
        System.out.println("请输入所有顶点：");
        for(int i=0;i<vexNum;i++){
            System.out.print("顶点：");
            String v = input.next();
            graph.vexs[i] = v;
        }
        System.out.println("请输入所有边：");
        for(int i=0;i<arcNum;i++){
            System.out.println("---------------------");
            System.out.print("请输入顶点v1：");
            String v1 = input.next();
            System.out.print("请输入顶点v2：");
            String v2 = input.next();
            int w = 1;
            if(hasWeight){
                System.out.print("请输入权值：");
                w = input.nextInt();
            }
            int n = locateVex(graph,v1);
            int m = locateVex(graph,v2);
            graph.arcs[n][m] = w;
            if(code == UDN_CODE){
                graph.arcs[m][n] = w;
            }
        }
        return graph;
    }


    /**
     * 创建邻接矩阵
     * @param code 无向图 or 有向图
     * @param hasWeight 是否有权值
     * @param vex 结点表
     * @param adj 边表
     * @return 邻接矩阵
     */
    public AdjMatrix createMatrix(int code, boolean hasWeight, List<String> vex, List<String> adj){
        assert code==0 || code==1;
        assert vex!=null && adj!=null;
        AdjMatrix matrix = new AdjMatrix(vex.size(), adj.size());
        matrix.vexs = vex.toArray(new String[0]);
        // 有权值的情况，矩阵初始化为 Integer.MAX_VALUE，否则为 0
        if(hasWeight){
            for(int i=0;i < matrix.vexNum;i++){
                Arrays.fill(matrix.arcs[i],Integer.MAX_VALUE);
            }
        }

        for(int i=0;i<adj.size();i++){
            String[] str = adj.get(i).split(" ");
            String v1 = str[0];
            String v2 = str[1];
            int w = 1;
            if(hasWeight){
                w = Integer.parseInt(str[2]);
            }
            int n = locateVex(matrix,v1);
            int m = locateVex(matrix,v2);
            matrix.arcs[n][m] = w;
            if(code == UDN_CODE){
                matrix.arcs[m][n] = w;
            }
        }

        return matrix;
    }

    int locateVex(AdjMatrix G,String v){
        int n = G.vexNum;
        for(int i=0;i<n;i++){
            if(v.equals(G.vexs[i])){
                return i;
            }
        }
        return 0;
    }

    public String printMatrix(AdjMatrix matrix){
        int n = matrix.vexNum;
        int[][] arcs = matrix.arcs;
        StringBuilder format = new StringBuilder();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(arcs[i][j] == Integer.MAX_VALUE){
                    format.append("∞");
                }else{
                    format.append(arcs[i][j]);
                }
                if(j != n-1){
                    format.append("\t");
                }
            }
            format.append("\n");
        }
        return format.toString();
    }

}
