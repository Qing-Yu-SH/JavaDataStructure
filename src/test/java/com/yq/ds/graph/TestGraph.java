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

    @Test
    public void test_primAM(){
        List<String> vex = Arrays.asList("A","B","C","D","E","F");
        List<String> adj = new ArrayList<>();
        adj.add("A B 5");
        adj.add("A F 2");
        adj.add("A C 6");
        adj.add("B F 7");
        adj.add("B D 3");
        adj.add("C F 1");
        adj.add("C E 2");
        adj.add("D F 8");
        adj.add("D E 6");
        adj.add("E F 9");
        ConstructAdjMatrix construct = new ConstructAdjMatrix();
        AdjMatrix matrix = construct.createMatrix(ConstructAdjMatrix.UDN_CODE, true, vex, adj);
        Prim prim = new Prim();
        prim.prim_AM(matrix);
    }

    @Test
    public void test_primAL(){
        List<String> vex = Arrays.asList("A","B","C","D","E","F");
        List<String> adj = new ArrayList<>();
        adj.add("A B 5");
        adj.add("A F 2");
        adj.add("A C 6");
        adj.add("B F 7");
        adj.add("B D 3");
        adj.add("C F 1");
        adj.add("C E 2");
        adj.add("D F 8");
        adj.add("D E 6");
        adj.add("E F 9");
        AdjacencyList adjacencyList = new AdjacencyList();
        adjacencyList.createAdjacencyList(AdjacencyList.UDN_CODE,true,vex,adj);
        Prim prim = new Prim();
        prim.prim_AL(adjacencyList);
    }

    @Test
    public void test_primAM2(){
        List<String> vex = Arrays.asList("A","B","C","D","E","F");
        List<String> adj = new ArrayList<>();
        adj.add("A B 5");
        adj.add("A F 2");
        adj.add("A C 6");
        adj.add("B F 7");
        adj.add("B D 3");
        adj.add("C F 1");
        adj.add("C E 2");
        adj.add("D F 8");
        adj.add("D E 6");
        adj.add("E F 9");
        ConstructAdjMatrix construct = new ConstructAdjMatrix();
        AdjMatrix matrix = construct.createMatrix(ConstructAdjMatrix.UDN_CODE, true, vex, adj);
        Prim2 prim = new Prim2();
        prim.prim_AM(matrix);
    }

    @Test
    public void test_primeAL2(){
        List<String> vex = Arrays.asList("A","B","C","D","E","F");
        List<String> adj = new ArrayList<>();
        adj.add("A B 5");
        adj.add("A F 2");
        adj.add("A C 6");
        adj.add("B F 7");
        adj.add("B D 3");
        adj.add("C F 1");
        adj.add("C E 2");
        adj.add("D F 8");
        adj.add("D E 6");
        adj.add("E F 9");
        AdjacencyList adjacencyList = new AdjacencyList();
        adjacencyList.createAdjacencyList(AdjacencyList.UDN_CODE,true,vex,adj);
        Prim2 prim = new Prim2();
        prim.prim_AL(adjacencyList);
    }

    @Test
    public void test_kruskalAM(){
        List<String> vex = Arrays.asList("A","B","C","D","E","F");
        List<String> adj = new ArrayList<>();
        adj.add("A B 5");
        adj.add("A F 2");
        adj.add("A C 6");
        adj.add("B F 7");
        adj.add("B D 3");
        adj.add("C F 1");
        adj.add("C E 2");
        adj.add("D F 8");
        adj.add("D E 6");
        adj.add("E F 9");
        ConstructAdjMatrix construct = new ConstructAdjMatrix();
        AdjMatrix matrix = construct.createMatrix(ConstructAdjMatrix.UDN_CODE, true, vex, adj);
        Kruskal kruskal = new Kruskal();
        kruskal.kruskal_AM(matrix);
    }

    @Test
    public void test_kruskalAL(){
        List<String> vex = Arrays.asList("A","B","C","D","E","F");
        List<String> adj = new ArrayList<>();
        adj.add("A B 5");
        adj.add("A F 2");
        adj.add("A C 6");
        adj.add("B F 7");
        adj.add("B D 3");
        adj.add("C F 1");
        adj.add("C E 2");
        adj.add("D F 8");
        adj.add("D E 6");
        adj.add("E F 9");
        AdjacencyList adjacencyList = new AdjacencyList();
        adjacencyList.createAdjacencyList(AdjacencyList.UDN_CODE,true,vex,adj);
        Kruskal kruskal = new Kruskal();
        kruskal.kruskal_AL(adjacencyList);
    }

    @Test
    public void test_kruskalAM2(){
        List<String> vex = Arrays.asList("A","B","C","D","E","F");
        List<String> adj = new ArrayList<>();
        adj.add("A B 5");
        adj.add("A F 2");
        adj.add("A C 6");
        adj.add("B F 7");
        adj.add("B D 3");
        adj.add("C F 1");
        adj.add("C E 2");
        adj.add("D F 8");
        adj.add("D E 6");
        adj.add("E F 9");
        ConstructAdjMatrix construct = new ConstructAdjMatrix();
        AdjMatrix matrix = construct.createMatrix(ConstructAdjMatrix.UDN_CODE, true, vex, adj);
        Kruskal2 kruskal = new Kruskal2();
        kruskal.kruskal_AM(matrix);
    }

    @Test
    public void test_kruskalAL2(){
        List<String> vex = Arrays.asList("A","B","C","D","E","F");
        List<String> adj = new ArrayList<>();
        adj.add("A B 5");
        adj.add("A F 2");
        adj.add("A C 6");
        adj.add("B F 7");
        adj.add("B D 3");
        adj.add("C F 1");
        adj.add("C E 2");
        adj.add("D F 8");
        adj.add("D E 6");
        adj.add("E F 9");
        AdjacencyList adjacencyList = new AdjacencyList();
        adjacencyList.createAdjacencyList(AdjacencyList.UDN_CODE,true,vex,adj);
        Kruskal2 kruskal = new Kruskal2();
        kruskal.kruskal_AL(adjacencyList);
    }

    @Test
    public void test_dijkstraAM(){
        List<String> vex = Arrays.asList("A","B","C","D","E");
        List<String> adj = new ArrayList<>();
        adj.add("A E 100");
        adj.add("A D 20");
        adj.add("A C 5");
        adj.add("B C 2");
        adj.add("C D 10");
        adj.add("D E 60");
        ConstructAdjMatrix construct = new ConstructAdjMatrix();
        AdjMatrix matrix = construct.createMatrix(ConstructAdjMatrix.DAG_CODE, true, vex, adj);
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.dijkstra_AM(matrix,0);
    }

    @Test
    public void test_dijkstraAL(){
        List<String> vex = Arrays.asList("A","B","C","D","E");
        List<String> adj = new ArrayList<>();
        adj.add("A E 100");
        adj.add("A D 20");
        adj.add("A C 5");
        adj.add("B C 2");
        adj.add("C D 10");
        adj.add("D E 60");
        AdjacencyList adjacencyList = new AdjacencyList();
        adjacencyList.createAdjacencyList(AdjacencyList.DAG_CODE,true,vex,adj);
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.dijkstra_AL(adjacencyList,0);
    }

    @Test
    public void test_dijkstraAM2(){
        List<String> vex = Arrays.asList("A","B","C","D","E");
        List<String> adj = new ArrayList<>();
        adj.add("A E 100");
        adj.add("A D 20");
        adj.add("A C 5");
        adj.add("B C 2");
        adj.add("C D 10");
        adj.add("D E 60");
        ConstructAdjMatrix construct = new ConstructAdjMatrix();
        AdjMatrix matrix = construct.createMatrix(ConstructAdjMatrix.DAG_CODE, true, vex, adj);
        Dijkstra2 dijkstra = new Dijkstra2();
        dijkstra.dijkstra_AM(matrix,0);
    }

    @Test
    public void test_dijkstraAL2(){
        List<String> vex = Arrays.asList("A","B","C","D","E");
        List<String> adj = new ArrayList<>();
        adj.add("A E 100");
        adj.add("A D 20");
        adj.add("A C 5");
        adj.add("B C 2");
        adj.add("C D 10");
        adj.add("D E 60");
        AdjacencyList adjacencyList = new AdjacencyList();
        adjacencyList.createAdjacencyList(AdjacencyList.DAG_CODE,true,vex,adj);
        Dijkstra2 dijkstra = new Dijkstra2();
        dijkstra.dijkstra_AL(adjacencyList,0);
    }

}
