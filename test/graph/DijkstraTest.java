package graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DijkstraTest {

    @Test
    void findShortestPath() {
        Dijkstra dijkstra = new Dijkstra();
        String source = "abcd", target = "acbe";
        int total = dijkstra.returnTotalCost(source, target);
        Assertions.assertEquals(28, total);
    }

    @Test
    void findTotalCost() {
        DijkstraDistances dijkstra = new DijkstraDistances();
        int[][] flights = {
                {0, 1, 100},
                {1, 2, 100},
                {2, 0, 100},
                {1, 3, 600},
                {2, 3, 200}
        };
        int total = dijkstra.findCheapestPrice(4, flights, 0, 3, 1);
        Assertions.assertEquals(700, total);
    }
}
