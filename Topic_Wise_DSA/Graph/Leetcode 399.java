import java.util.*;

//
// ✅ Problem: Evaluate Division (LeetCode #399)
// 🔗 Link: https://leetcode.com/problems/evaluate-division/
//
// 🧠 Approach:
// - Treat each equation as a directed graph where variables are nodes.
// - For example, "a / b = 2.0" means a directed edge from 'a' to 'b' with weight 2.0.
// - Also, "b / a = 1/2.0" creates a reverse edge from 'b' to 'a' with weight 0.5.
// - To solve each query, perform DFS to find a path from source to destination and multiply the weights along the path.
// - If no path exists between the variables, return -1.0.
//
// 🧮 Time Complexity: O(N + Q * V)
//      - N = number of equations
//      - Q = number of queries
//      - V = number of variables (DFS depth in worst case)
// 🧮 Space Complexity: O(N)
//      - For storing the graph and visited set
//

class Solution {

    // DFS function to find a path from 'src' to 'dst' and calculate the multiplication of weights along the path
    public void dfs(Map<String, List<AbstractMap.SimpleEntry<String, Double>>> adj,
                    String src, String dst, Set<String> visited, double product, double[] ans) {

        // If the current node is already visited, avoid cycles
        if (visited.contains(src)) return;

        // Mark the current node as visited
        visited.add(src);

        // If destination is reached, store the result and return
        if (src.equals(dst)) {
            ans[0] = product;
            return;
        }

        // If there are no outgoing edges from current node, stop exploring
        if (!adj.containsKey(src)) return;

        // Explore all neighbors of the current node
        for (AbstractMap.SimpleEntry<String, Double> neighbor : adj.get(src)) {
            String nextNode = neighbor.getKey();
            double nextVal = neighbor.getValue();

            // Recursive DFS call with updated product
            dfs(adj, nextNode, dst, visited, product * nextVal, ans);
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        // Construct the graph: Each node maps to a list of (neighbor, value) pairs
        Map<String, List<AbstractMap.SimpleEntry<String, Double>>> adj = new HashMap<>();

        // Convert each equation into two graph edges
        for (int i = 0; i < equations.size(); i++) {
            String u = equations.get(i).get(0); // numerator
            String v = equations.get(i).get(1); // denominator
            double val = values[i];

            adj.putIfAbsent(u, new ArrayList<>());
            adj.putIfAbsent(v, new ArrayList<>());

            // Add both forward and reverse edges
            adj.get(u).add(new AbstractMap.SimpleEntry<>(v, val));
            adj.get(v).add(new AbstractMap.SimpleEntry<>(u, 1.0 / val));
        }

        double[] result = new double[queries.size()];

        // Process each query using DFS
        for (int i = 0; i < queries.size(); i++) {
            String src = queries.get(i).get(0);
            String dst = queries.get(i).get(1);

            double[] ans = new double[]{-1.0}; // Default result if path not found

            // Only perform DFS if the source variable exists in the graph
            if (adj.containsKey(src)) {
                Set<String> visited = new HashSet<>();
                dfs(adj, src, dst, visited, 1.0, ans);
            }

            result[i] = ans[0];
        }

        return result;
    }
}
