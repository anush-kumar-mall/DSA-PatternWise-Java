import java.util.*;


// ✅ Problem: Network Delay Time (LeetCode #743)
// 🔗 Link: https://leetcode.com/problems/network-delay-time/
//
// 🧠 Approach:
// - Use Dijkstra's Algorithm to find the shortest time from source node `k` to all other nodes.
// - Build an adjacency list from the given edges.
// - Use a min-heap (priority queue) to process the node with the smallest known distance.
// - Update distances if a shorter path is found.
// - After processing all reachable nodes, find the maximum distance among them.
// - If any node is unreachable, return -1.
//
// 🧮 Time Complexity: O(E log V) where E = edges, V = nodes (due to priority queue)
// 🧮 Space Complexity: O(N + E) for adjacency list and distance array

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // Adjacency list to represent the graph
        Map<Integer, List<int[]>> adj = new HashMap<>();

        // Build the graph from edge list
        for (int[] time : times) {
            int u = time[0]; // source node
            int v = time[1]; // destination node
            int w = time[2]; // delay time (weight)

            adj.putIfAbsent(u, new ArrayList<>());
            adj.get(u).add(new int[]{v, w});
        }

        // Min-heap: [current_distance, current_node]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Distance array initialized to "infinity"
        int[] dist = new int[n + 1]; // 1-based indexing
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0; // Distance to source is 0
        pq.offer(new int[]{0, k});

        // Dijkstra's algorithm
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int d = current[0];
            int node = current[1];

            if (!adj.containsKey(node)) continue;

            // Explore neighbors
            for (int[] neighbor : adj.get(node)) {
                int nextNode = neighbor[0];
                int weight = neighbor[1];

                // Relaxation step
                if (d + weight < dist[nextNode]) {
                    dist[nextNode] = d + weight;
                    pq.offer(new int[]{dist[nextNode], nextNode});
                }
            }
        }

        // Find the maximum time to reach any node
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1; // Node unreachable
            ans = Math.max(ans, dist[i]);
        }

        return ans;
    }
}
