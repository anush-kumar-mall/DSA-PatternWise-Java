// ✅ Problem: Minimum Height Trees (LeetCode #310)
// 🔗 Link: https://leetcode.com/problems/minimum-height-trees/
//
// 🧠 Approach:
// - The center(s) of the tree will give minimum height when used as root.
// - We use a BFS-based topological sort to trim leaves layer by layer.
// - Start with all leaf nodes (indegree = 1).
// - In each round, remove current leaves and update their neighbors.
// - The last remaining 1 or 2 nodes are the roots of MHTs.
//
// 🧮 Time Complexity: O(n)
// 🧮 Space Complexity: O(n)

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1)
            return Collections.singletonList(0); // Edge case: only one node
        
        List<Integer> result = new ArrayList<>();
        int[] indegree = new int[n];
        Map<Integer, List<Integer>> adj = new HashMap<>();

        // Build the adjacency list and indegree count
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            indegree[u]++;
            indegree[v]++;
            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        // Start with all leaf nodes (degree 1)
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 1)
                que.offer(i);
        }

        // BFS: remove leaves layer by layer
        while (n > 2) {
            int size = que.size();
            n -= size; // Remove current layer of leaves
            while (size-- > 0) {
                int u = que.poll();
                for (int v : adj.getOrDefault(u, Collections.emptyList())) {
                    indegree[v]--;
                    if (indegree[v] == 1)
                        que.offer(v); // v becomes a new leaf
                }
            }
        }

        // Remaining 1 or 2 nodes are roots of minimum height trees
        while (!que.isEmpty()) {
            result.add(que.poll());
        }

        return result;
    }
}
