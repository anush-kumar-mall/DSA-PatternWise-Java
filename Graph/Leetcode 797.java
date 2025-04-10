import java.util.*;


// ✅ Problem: All Paths From Source to Target (LeetCode #797)
// 🔗 Link: https://leetcode.com/problems/all-paths-from-source-to-target/
//
// 🧠 Approach:
// - Convert the input graph to an adjacency list for easy traversal.
// - Use DFS to explore all possible paths from source (node 0) to target (node n - 1).
// - Maintain a path list to store the current path.
// - Once destination is reached, add a copy of the path to the result.
// - Backtrack by removing the last node from the path after exploring.
//
// 🧮 Time Complexity: O(2^n * n) → Exponential due to all possible paths, and each path can take up to O(n) space
// 🧮 Space Complexity: O(n) → Depth of recursion stack and path storage

class Solution {

    // ✅ DFS helper function to explore all possible paths
    public void dfs(List<List<Integer>> adj, int currentNode, List<Integer> pathSoFar, List<List<Integer>> allPaths) {
        pathSoFar.add(currentNode); // Add current node to the path

        // Base case: if we reached the destination node
        if (currentNode == adj.size() - 1) {
            allPaths.add(new ArrayList<>(pathSoFar)); // Add copy of path to result
        } else {
            // Recurse for all neighbors of the current node
            for (int neighbor : adj.get(currentNode)) {
                dfs(adj, neighbor, pathSoFar, allPaths);
            }
        }

        // Backtrack: remove last node before returning
        pathSoFar.remove(pathSoFar.size() - 1);
    }

    // ✅ Main function to return all paths from source (0) to target (n - 1)
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length;

        // Step 1: Convert graph into adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            for (int neighbor : graph[i]) {
                adj.get(i).add(neighbor);
            }
        }

        // Step 2: Prepare result list and start DFS from node 0
        List<List<Integer>> result = new ArrayList<>();
        dfs(adj, 0, new ArrayList<>(), result);

        return result;
    }
}
