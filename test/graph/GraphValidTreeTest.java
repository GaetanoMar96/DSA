package graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GraphValidTreeTest {


    @Test
    void treeIsValid() {
        GraphValidTree gt = new GraphValidTree();
        int[][] edges = {{0,1},{0,2},{0,3},{1,4}};
        int n = 5;
        Assertions.assertTrue(gt.isGraphValidTree(n, edges));
    }

    @Test
    void treeIsNotValid_CycleFound() {
        GraphValidTree gt = new GraphValidTree();
        int[][] edges = {{0,1},{1,2},{2,3},{1,3},{1,4}};
        int n = 5;
        Assertions.assertFalse(gt.isGraphValidTree(n, edges));
    }

    @Test
    void treeIsNotValid_MoreConnComponents() {
        GraphValidTree gt = new GraphValidTree();
        int[][] edges = {{0,1},{2,3}};
        int n = 4;
        Assertions.assertFalse(gt.isGraphValidTree(n, edges));
    }

    @Test
    void graphHasCycle() {
        GraphValidTree gt = new GraphValidTree();
        int[][] edges = {{0,1},{1,2},{2,3},{3,4},{2,4},{4,0}};
        Assertions.assertTrue(gt.hasDirectedGraphCycle(edges));
    }

    @Test
    void graphHasNotCycle() {
        GraphValidTree gt = new GraphValidTree();
        int[][] edges = {{0,1},{0,2},{0,3},{1,4}};
        Assertions.assertFalse(gt.hasDirectedGraphCycle(edges));
    }
}
