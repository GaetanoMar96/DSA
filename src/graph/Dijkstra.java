package graph;

import java.util.*;

public class Dijkstra {

    private final Map<Character, Map<Character, Integer>> adj = new HashMap<>();

    public int returnTotalCost(String source, String target) {
        Character[] original = {'a','b','c','c','e','d'};
        Character[] changed = {'b','c','b','e','b','e'};
        int[] cost = {2,5,5,1,2,20};
        for (int i = 0; i < original.length; i++){
            Character cho = original[i];
            Character chc = changed[i];
            int c = cost[i];
            if (adj.containsKey(cho) && adj.get(cho).containsKey(chc)) {
                Map<Character, Integer> prev = adj.get(cho);
                if (c < prev.get(chc))
                    adj.put(cho, Map.of(chc, c));
            } else {
                Map<Character, Integer> computed = Map.of(chc, c);
                adj.put(cho, computed);
            }
        }
        int total = 0;
        for (int i = 0; i < source.length(); i++) {
            int path = findShortestPath(source.charAt(i), target.charAt(i));
            if (path == Integer.MAX_VALUE)
                break;
            total += path;
        }
        return total;
    }

    /**
     * Shortest path between node source and target. Use a priority queue. Then start searching,
     * keep track of visited nodes, when you find the target return the cost to reach the target.
     * The cost is updated while doing the loop as new cost = curr cost + weight of edge
     * @param source starting node
     * @param target ending node
     * @return minimum total cost
     */
    public int findShortestPath(Character source, Character target) {
        PriorityQueue<Pair<Character>> pq = new PriorityQueue<>(
                Comparator.comparingInt(pair -> pair.cost)
        );
        pq.add(new Pair<>(source, 0));
        Set<Character> visited = new HashSet<>();
        while(!pq.isEmpty()) {
            Pair<Character> pair = pq.poll();
            if (Objects.equals(pair.node, target)) {
                return pair.cost;
            }
            if (visited.contains(pair.node))
                continue;
            visited.add(pair.node);
            for (Map.Entry<Character, Integer> entry : adj.get(pair.node).entrySet()) {
                if (!visited.contains(entry.getKey())) {
                    pq.add(new Pair<>(entry.getKey(), entry.getValue() + pair.cost));
                }
            }
        }
        return Integer.MAX_VALUE;
    }
}
