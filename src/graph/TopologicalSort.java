package graph;

import java.util.*;

public class TopologicalSort {

    static Map<Integer, List<Integer>> adj = new HashMap<>();

    static Map<Integer, Integer> indegree = new HashMap<>();

    /**
     * Topological sort algo in a DAG. First compute indegree, then while collecting the directed
     * edges update indegree for the node which have uncoming edges.
     * Then fill a deque with all the nodes without indegree.
     * At the end perform a bfs.
     * @param args
     */
    public static void main(String[] args) {
        int courses = 5;
        int[][] prerequisites = {{1,4},{2,4},{3,1},{3,2}};
        for (int i = 0; i < courses; i++){
            indegree.put(i, 0);
        }
        for (int[] prerequisite : prerequisites) {
            int a = prerequisite[0];
            int b = prerequisite[1];
            adj.computeIfAbsent(b, key -> new ArrayList<>()).add(a);
            indegree.put(a, indegree.get(a) + 1);
        }
        Deque<Integer> q = new ArrayDeque<>();
        for(int key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                q.addLast(key);
            }
        }

        int completedCourses = getTotalCourses(q);
        System.out.println(completedCourses == courses);
    }

    public static int getTotalCourses(Deque<Integer> q) {
        int courses = 0;
        while (!q.isEmpty()) {
            int node = q.removeFirst();
            courses += 1;
            if (adj.containsKey(node)) {
                for (Integer neigh : adj.get(node)) {
                    if (indegree.containsKey(neigh)) {
                        indegree.put(neigh, indegree.get(neigh) - 1);
                        if (indegree.get(neigh) == 0)
                            q.addLast(neigh);
                    }
                }
            }
        }
        return courses;
    }
}
