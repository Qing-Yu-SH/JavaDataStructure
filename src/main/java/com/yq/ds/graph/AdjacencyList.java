package com.yq.ds.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: JavaDataStructure
 * @description: 邻接表
 * @author: Yuqing
 * @create: 2023-06-13 17:11
 **/
public class AdjacencyList {

    // 邻接表
    HeadNode[] nodes;
    // 结点 -> 索引
    private Map<String,Integer> map;
    // 结点数
    int vexNum;
    // 是否包含权重
    boolean hasWeight;

    public static final int UDN_CODE = 0;
    public static final int DAG_CODE = 1;

    /**
     * 创建邻接表
     * @param code 无向图 or 有向图
     * @param hasWeight 是由有权值
     */
    public void createAdjacencyList(int code,boolean hasWeight){
        assert code==0 || code==1;
        Scanner input=new Scanner(System.in);
        System.out.print("请输入顶点数：");
        int vexNum = input.nextInt();
        System.out.print("请输入边数：");
        int arcNum = input.nextInt();
        this.vexNum = vexNum;
        this.hasWeight = hasWeight;
        nodes = new HeadNode[vexNum];
        map = new HashMap<>();
        int curNum = 0;

        System.out.println("请输入所有边：");
        for(int i=0;i<arcNum;i++){
            System.out.println("---------------------");
            System.out.print("请输入顶点v1：");
            String v1 = input.next();
            System.out.print("请输入顶点v2：");
            String v2 = input.next();
            int w = 0;
            if(hasWeight){
                System.out.print("请输入权值：");
                w = input.nextInt();
            }

            int v1Index = locVex(v1);
            int v2Index = locVex(v2);
            // 结点不存在，新建结点
            if(v1Index == -1){
                v1Index = createNode(curNum,v1);
                curNum++;
            }
            if(v2Index == -1){
                v2Index = createNode(curNum,v2);
                curNum++;
            }

            // 保存边信息
            connect(v1Index,v2Index,w);
            if(code == UDN_CODE){
                connect(v2Index,v1Index,w);
            }

        }
    }


    public void createAdjacencyList(int code, boolean hasWeight, List<String> vex,List<String> adj){
        assert code==0 || code==1;
        assert vex!=null && adj!=null;
        this.vexNum = vex.size();
        this.hasWeight = hasWeight;
        nodes = new HeadNode[vexNum];
        map = new HashMap<>();

        // 建立头结点表
        for(int i=0;i<vexNum;i++){
            nodes[i] = new HeadNode(vex.get(i));
            map.put(vex.get(i),i);
        }

        for (String s : adj) {
            String[] str = s.split(" ");
            String v1 = str[0];
            String v2 = str[1];
            int w = 0;
            if (hasWeight) {
                w = Integer.parseInt(str[2]);
            }
            int v1Index = locVex(v1);
            int v2Index = locVex(v2);

            // 保存边信息
            connect(v1Index, v2Index, w);
            if (code == UDN_CODE) {
                connect(v2Index, v1Index, w);
            }
        }
    }

    /**
     * 定位结点在 nodes 中的索引
     * @param vex 结点
     * @return 索引
     */
    private int locVex(String vex){
        return map.getOrDefault(vex, -1);
    }

    /**
     * 创建一个表头结点
     * @param pos 索引
     * @param vex 结点
     * @return 索引
     */
    private int createNode(int pos,String vex){
        this.nodes[pos] = new HeadNode(vex);
        map.put(vex,pos);
        return pos;
    }

    /**
     * 将索引为 v1 的表头结点，建立边结点
     * @param v1 起点
     * @param v2 终点
     * @param w 权值
     */
    private void connect(int v1,int v2,int w){
        HeadNode node = nodes[v1];
        if(node.firstArc == null){
            node.firstArc = new EdgeNode(v2,w);
        }else{
            EdgeNode edgeNode = node.firstArc;
            while(edgeNode.nextArc != null){
                edgeNode = edgeNode.nextArc;
            }
            edgeNode.nextArc = new EdgeNode(v2,w);
        }
    }

    /**
     * 根据索引获取结点值
     * @param pos 索引
     * @return 结点值
     */
    private String getDataByPos(int pos){
        return nodes[pos].data;
    }

    /**
     * 打印邻接表
     */
    public void print(){
        assert nodes != null;
        System.out.println("\n邻接表信息：");
        System.out.println("表头结点\t边信息");
        for (HeadNode node: nodes){
            StringBuilder sb = new StringBuilder();
            sb.append(node.data).append(" -->  ").append("\t");
            EdgeNode edgeNode = node.firstArc;
            while(edgeNode != null){
                sb.append(getDataByPos(edgeNode.adjVex));
                if(hasWeight){
                    sb.append(" : ").append(edgeNode.w);
                }
                sb.append("\t");
                edgeNode = edgeNode.nextArc;
            }
            System.out.println(sb.toString());
        }
    }

    /**
     * 打印邻接表
     */
    public void printWithIndex(){
        assert nodes != null;
        System.out.println("\n邻接表信息：");
        System.out.println("表头结点\t边信息");
        for (int i=0;i<nodes.length;i++){
            HeadNode node = nodes[i];
            StringBuilder sb = new StringBuilder();
            sb.append(i).append(" : ").append(node.data).append(" -->  ").append("\t");
            EdgeNode edgeNode = node.firstArc;
            while(edgeNode != null){
                sb.append(edgeNode.adjVex);
                if(hasWeight){
                    sb.append(" : ").append(edgeNode.w);
                }
                sb.append("\t");
                edgeNode = edgeNode.nextArc;
            }
            System.out.println(sb.toString());
        }
    }


    // 表头结点
    class HeadNode{
        String data;
        EdgeNode firstArc;

        public HeadNode(String data) {
            this.data = data;
        }
    }

    // 边结点
    class EdgeNode{
        int adjVex;
        int w;
        EdgeNode nextArc;

        public EdgeNode(int adjVex) {
            this.adjVex = adjVex;
        }

        public EdgeNode(int adjVex, int w) {
            this.adjVex = adjVex;
            this.w = w;
        }
    }
}
