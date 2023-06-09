package com.yq.ds.graph;

import org.junit.Test;

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
}
