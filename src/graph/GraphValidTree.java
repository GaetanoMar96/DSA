package graph;

import java.util.*;

public class GraphValidTree {

    private static Map<Integer, List<Integer>> adj;

    /**
     * Method to check if the tree is valid.
     * A tree is a special type of graph, without cycle in it
     * and with no more than 1 connected components
     * @return true if valid otherwise false
     */
    public boolean isGraphValidTree(int nodes, int[][] edges) {
        adj = new HashMap<>();
        //build an undirected graph starting from edges
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }
        for(int i = 0; i < nodes; i++) {
            Set<Integer> visited = new HashSet<>();
            if(dfs(i, -1, visited)) { //has a cycle
                return false;
            }
            if(visited.size() != nodes) { //more than 1 connected component
                return false;
            }
        }
        return true;
    }

    /**
     * Method to check if a directed graph has a cycle in it.
     * @return true if has cycle otherwise false
     */
    public boolean hasDirectedGraphCycle(int[][] edges) {
        adj = new HashMap<>();
        //build a directed graph starting from edges
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        }
        Set<Integer> visited = new HashSet<>();
        for(Integer key : adj.keySet()) {
            if(hasCycle(key, visited, new HashSet<>())) { //has a cycle
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param node current node
     * @param parent parent node
     * @param visited set to keep track of visited nodes
     * @return true if a cycle is found
     */
    public boolean dfs(int node, int parent, Set<Integer> visited) {
        visited.add(node);
        for(Integer neigh : adj.get(node)){
            if(visited.contains(neigh) && neigh != parent) {
                return true;
            }
            if(!visited.contains(neigh) && dfs(neigh, node, visited)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param node current node
     * @param visited set to keep track of visited nodes
     * @param visiting set to keep track of current visited nodes
     * @return true if a cycle is found
     */
    public boolean hasCycle(int node, Set<Integer> visited, Set<Integer> visiting) {
        if(visiting.contains(node))
            return true;
        if(visited.contains(node))
            return false;
        visiting.add(node);
        if (adj.containsKey(node)) {
            for(Integer neigh : adj.get(node)){
                if(hasCycle(neigh, visited, visiting)) {
                    return true;
                }
            }
        }
        visiting.remove(node);
        visited.add(node);
        return false;
    }
}
