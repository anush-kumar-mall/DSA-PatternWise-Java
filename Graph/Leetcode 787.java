import java.util.*;


// ✅ Problem: Cheapest Flights Within K Stops (LeetCode #787)
// 🔗 Link: https://leetcode.com/problems/cheapest-flights-within-k-stops/
//
// 🧠 Approach:
// - Use a BFS-like approach to track the minimum cost path with at most `k` stops.
// - Build an adjacency list where each entry has a destination and cost.
// - Use a queue to process nodes level-by-level (like in BFS), tracking how many stops we have taken so far.
// - On each level, for every node, try to relax the cost to its neighbors.
// - If a cheaper cost is found, update and push the neighbor to queue.
// - After `k` levels, check the final cost to the destination node.
//
// 🧮 Time Complexity: O(k * E) → At each level, we might explore all edges
// 🧮 Space Complexity: O(V + E) → For adjacency list and queue

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Step 1: Initialize distance array with MAX_VALUE
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[src] = 0; // source to source distance is 0

        // Step 2: Build adjacency list
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int[] flight : flights) {
            int u = flight[0], v = flight[1], cost = flight[2];
            adj.computeIfAbsent(u, key -> new ArrayList<>()).add(new int[]{v, cost});
        }

        // Step 3: BFS traversal with level control (like Dijkstra with stop limit)
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src, 0}); // {currentNode, totalCost}
        int level = 0;

        while (!queue.isEmpty() && level <= k) {
            int size = queue.size();

            // Copy of distance to keep updates isolated to this level
            int[] temp = Arrays.copyOf(distance, n);

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int u = current[0];
                int currentCost = current[1];

                // Explore neighbors
                for (int[] neighbor : adj.getOrDefault(u, Collections.emptyList())) {
                    int v = neighbor[0];
                    int edgeCost = neighbor[1];

                    if (currentCost + edgeCost < temp[v]) {
                        temp[v] = currentCost + edgeCost;
                        queue.offer(new int[]{v, temp[v]});
                    }
                }
            }

            distance = temp; // update distance for the next level
            level++;
        }

        return distance[dst] == Integer.MAX_VALUE ? -1 : distance[dst];
    }
}
