package graph;

import java.util.*;

/**
 * There are n cities connected by some number of flights.
 * You are given an array flights where flights[i] = [fromi, toi, pricei]
 * indicates that there is a flight from city from i to city to i with cost price i.
 * You are also given three integers src, dst, and k,
 * return the cheapest price from src to dst with at most k stops.
 * If there is no such route, return -1.
 */
public class DijkstraDistances {

    private final Map<Integer, List<Pair<Integer>>> adjList = new HashMap<>();

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        setAdjList(flights);
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.offer(new int[]{src, 0, 0}); //source, cost, stops
        while(!pq.isEmpty()) {
            int[] current = pq.poll();
            int source = current[0];
            int stop = current[2];
            if(source == dst) {
                return current[1]; //if destination return current cost
            }
            //check distances if already computed
            if(distances[source] <= stop)
                continue;
            distances[source] = stop;
            if(stop <= k && adjList.containsKey(source)) {
                List<Pair<Integer>> pairs = adjList.get(source);
                for(Pair<Integer> pair : pairs) {
                    pq.offer(new int[]{pair.node, pair.cost + current[1], stop + 1});
                }
            }
        }
        return -1;
    }

    public void setAdjList(int[][] flights) {
        for(int[] flight : flights) {
            int dest, cost;
            dest = flight[1];
            cost = flight[2];
            adjList.computeIfAbsent(flight[0], k -> new ArrayList<>()).add(new Pair<>(dest, cost));
        }
    }
}
