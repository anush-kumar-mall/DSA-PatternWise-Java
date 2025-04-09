import java.util.*;


// ✅ Problem: Is Graph Bipartite? (LeetCode #785)
// 🔗 Link: https://leetcode.com/problems/is-graph-bipartite/
//
// 🧠 Approach:
// - A graph is bipartite if we can color it using 2 colors such that no two adjacent nodes have the same color.
// - Use BFS to try to color the graph.
// - Assign one color (say 1) to the starting node, and alternate colors (1 and -1) for neighbors.
// - If we find any neighbor already colored with the same color as the current node, return false.
// - If we are able to color the entire graph without conflict, return true.
//
// 🧮 Time Complexity: O(V + E) where V = number of nodes, E = number of edges
// 🧮 Space Complexity: O(V) for color array and queue

class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;

        // Color array: 0 = not colored, 1 and -1 are two different colors
        int[] colors = new int[n];

        // Check each component of the graph
        for (int i = 0; i < n; i++) {
            if (colors[i] != 0) continue; // already colored

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            colors[i] = 1; // Start coloring with 1

            while (!queue.isEmpty()) {
                int current = queue.poll();

                // Check all neighbors
                for (int neighbor : graph[current]) {
                    // If neighbor is not colored, assign opposite color
                    if (colors[neighbor] == 0) {
                        colors[neighbor] = -colors[current];
                        queue.offer(neighbor);
                    }
                    // If neighbor has same color → not bipartite
                    else if (colors[neighbor] == colors[current]) {
                        return false;
                    }
                }
            }
        }

        return true; // All nodes properly colored → graph is bipartite
    }
}
