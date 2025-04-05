// ✅ Problem: Find if Path Exists in Graph (LeetCode #1971)
// 🔗 Link: https://leetcode.com/problems/find-if-path-exists-in-graph/
//
// 🧠 Approach: 
// - Graph ko adjacency list me convert karo.
// - DFS (Depth First Search) use karke source se destination tak pahuchne ki koshish karo.
// - Agar pahuch gaye → path exist karta hai ✅
// - Agar visited nodes repeat hone lage ya destination nahi mila → false ❌
//
// 🧮 Time Complexity: O(V + E) → V = nodes, E = edges
// 🧮 Space Complexity: O(V) → visited array + recursion stack

import java.util.*;

class Solution {
    
    // 🔁 DFS function to check if path exists from 'node' to 'dest'
    private boolean check(Map<Integer, List<Integer>> mp, int node, int dest, boolean[] visited) {
        if (node == dest) {
            return true; // ✅ Destination mil gaya
        }
        
        if (visited[node]) {
            return false; // ❌ Node already visited → cycle ya dead end
        }
        
        visited[node] = true; // ✅ Mark this node as visited

        // 🔁 Traverse all neighbors (adjacent nodes)
        for (int neighbor : mp.getOrDefault(node, new ArrayList<>())) {
            if (check(mp, neighbor, dest, visited)) {
                return true; // ✅ Kisi neighbor se destination mil gaya
            }
        }
        return false; // ❌ Destination nahi mila kisi path se
    }
    
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // ⚡ Base case: source == destination
        if (source == destination) {
            return true;
        }
        
        // 🔧 Step 1: Graph ko build karo (adjacency list)
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            
            mp.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            mp.computeIfAbsent(v, k -> new ArrayList<>()).add(u); // ❗Undirected graph hai
        }
        
        // 🔍 Step 2: DFS call with visited array
        boolean[] visited = new boolean[n]; // To avoid cycles
        return check(mp, source, destination, visited);
    }
}
