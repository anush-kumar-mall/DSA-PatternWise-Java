import java.util.*;


// ✅ Problem: Number of Provinces (LeetCode #547)
// 🔗 Link: https://leetcode.com/problems/number-of-provinces/
//
// 🧠 Approach:
// - Treat the isConnected matrix as an adjacency matrix for an undirected graph.
// - Convert the matrix into an adjacency list for easier traversal.
// - Use DFS to explore all nodes in a connected component.
// - Count how many times a DFS starts from an unvisited node — that gives the number of provinces.
//
// 🧮 Time Complexity: O(n^2) - for converting the matrix + DFS traversal
// 🧮 Space Complexity: O(n) - for visited array and adjacency list

class Solution {

    // DFS function to explore a single connected component
    private void dfs(Map<Integer, List<Integer>> adj, int u, boolean[] visited) {
        visited[u] = true; // Mark the current node as visited

        // Visit all unvisited neighbours
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                dfs(adj, v, visited); // Recursively visit the neighbor
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;

        // Convert adjacency matrix to adjacency list
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adj.put(i, new ArrayList<>());
        }

        // Fill adjacency list (undirected graph)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1 && i != j) {
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }

        boolean[] visited = new boolean[n]; // Track visited nodes
        int count = 0; // Number of connected components (provinces)

        // For every node, if not visited, start DFS
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++; // Found a new province
                dfs(adj, i, visited); // Explore the whole component
            }
        }

        return count; // Return total number of provinces
    }
}
