package com.yq.ds.graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: JavaDataStructure
 * @description:
 * @author: Yuqing
 * @create: 2023-06-09 22:12
 **/
public class TestGraph {

    @Test
    public void test_adjMatrix(){
        ConstructAdjMatrix construct = new ConstructAdjMatrix();
        AdjMatrix matrix = construct.createMatrix(ConstructAdjMatrix.DAG_CODE, true);
        System.out.println(construct.printMatrix(matrix));
    }

    @Test
    public void test_adjMatrix2(){
        List<String> vex = Arrays.asList("A","B","C","D","E");
        List<String> adj = new ArrayList<>();
        adj.add("A B 2");
        adj.add("A C 6");
        adj.add("A E 2");
        adj.add("C A 5");
        adj.add("C D 1");
        adj.add("D B 8");
        adj.add("D E 7");
        adj.add("E B 9");
        ConstructAdjMatrix construct = new ConstructAdjMatrix();
        AdjMatrix matrix = construct.createMatrix(ConstructAdjMatrix.DAG_CODE, true,vex,adj);
        System.out.println(construct.printMatrix(matrix));
    }

    @Test
    public void test_adjacencyList(){
        AdjacencyList adjacencyList = new AdjacencyList();
        adjacencyList.createAdjacencyList(AdjacencyList.UDN_CODE,false);
        adjacencyList.print();
    }

    @Test
    public void test_adjacencyList2(){
        List<String> vex = Arrays.asList("A","B","C","D","E");
        List<String> adj = new ArrayList<>();
        adj.add("A B");
        adj.add("A C");
        adj.add("B E");
        adj.add("C E");
        adj.add("C D");
        adj.add("D E");
        AdjacencyList adjacencyList = new AdjacencyList();
        adjacencyList.createAdjacencyList(AdjacencyList.UDN_CODE,false,vex,adj);
        adjacencyList.printWithIndex();
    }

    @Test
    public void test_dfs(){
        List<String> vex = Arrays.asList("A","B","C","D","E","F","G","H");
        List<String> adj = new ArrayList<>();
        adj.add("A B");
        adj.add("A C");
        adj.add("B D");
        adj.add("B E");
        adj.add("C G");
        adj.add("C H");
        adj.add("G H");
        adj.add("D F");
        adj.add("E F");
        ConstructAdjMatrix construct = new ConstructAdjMatrix();
        AdjMatrix matrix = construct.createMatrix(ConstructAdjMatrix.UDN_CODE, false,vex,adj);
        AdjacencyList adjacencyList = new AdjacencyList();
        adjacencyList.createAdjacencyList(AdjacencyList.UDN_CODE,false,vex,adj);

        DepthFirstSearch.DFS(matrix);
        DepthFirstSearch.DFS(adjacencyList);
    }

    @Test
    public void test_bfs(){
        List<String> vex = Arrays.asList("A","B","C","D","E","F","G","H");
        List<String> adj = new ArrayList<>();
        adj.add("A B");
        adj.add("A C");
        adj.add("B D");
        adj.add("B E");
        adj.add("C G");
        adj.add("C H");
        adj.add("G H");
        adj.add("D F");
        adj.add("E F");
        ConstructAdjMatrix construct = new ConstructAdjMatrix();
        AdjMatrix matrix = construct.createMatrix(ConstructAdjMatrix.UDN_CODE, false,vex,adj);
        AdjacencyList adjacencyList = new AdjacencyList();
        adjacencyList.createAdjacencyList(AdjacencyList.UDN_CODE,false,vex,adj);

        BreadthFirstSearch.BFS(matrix);
        BreadthFirstSearch.BFS(adjacencyList);
    }
}
