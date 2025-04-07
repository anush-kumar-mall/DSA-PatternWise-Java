import java.util.*;


// ✅ Problem: Redundant Connection (LeetCode #684)
// 🔗 Link: https://leetcode.com/problems/redundant-connection/
//
// 🧠 Approach:
// - Use Disjoint Set Union (Union-Find) to detect a cycle in an undirected graph.
// - Initialize parent and rank arrays for all nodes.
// - For each edge, check if the two nodes belong to the same set using `find()`.
//   - If they do, that edge is redundant (forms a cycle).
//   - If not, union the two sets.
// - Path compression and union by rank are used for optimization.
//
// 🧮 Time Complexity: O(n * α(n)) ≈ O(n) where α(n) is the inverse Ackermann function
// 🧮 Space Complexity: O(n)

class DSU {
    int[] parent;
    int[] rank;

    public DSU(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i; // har node initially apna khud ka parent hai
            rank[i] = 0;   // sabki rank initially 0 hai
        }
    }

    // Path Compression: root parent tak pohchane mein shortcut banata hai
    public int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]); // recursively root find karo aur parent update karo
        }
        return parent[x];
    }

    // Union by Rank: chhoti height wale tree ko bade height wale mein jodte hain
    public void union(int x, int y) {
        int x_parent = find(x);
        int y_parent = find(y);

        if (x_parent == y_parent) return; // already same set mein hain

        if (rank[x_parent] > rank[y_parent]) {
            parent[y_parent] = x_parent;
        } else if (rank[y_parent] > rank[x_parent]) {
            parent[x_parent] = y_parent;
        } else {
            parent[y_parent] = x_parent;
            rank[x_parent]++;
        }
    }
}

class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        DSU dsu = new DSU(n);

        // Har edge ke liye check karo agar cycle ban rahi hai
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (dsu.find(u) == dsu.find(v)) {
                return edge; // agar dono same component mein hain toh cycle ban rahi hai
            }
            dsu.union(u, v); // otherwise union kar do
        }

        return new int[0]; // ideally yahan nahi aana chahiye, question ne cycle guarantee ki hai
    }
}
