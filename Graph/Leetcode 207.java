// ✅ Problem: Course Schedule (LeetCode #207)
// 🔗 https://leetcode.com/problems/course-schedule/
//
// 🧠 Approach: Topological Sort using BFS (Kahn’s Algorithm)
//
// Har course ek node hai
// Agar koi course kisi course pe depend karta hai -> edge b se a (b → a)
// Agar hum topological sort kar lete hain bina cycle ke -> return true
// Agar cycle milti hai -> false

// ⏱️ Time Complexity: O(V + E)
// 📦 Space Complexity: O(V + E)

import java.util.*;

class Solution {

    // 📌 Ye function check karta hai topological sort possible hai ya nahi
    public boolean topologicalSortCheck(Map<Integer, List<Integer>> adj, int n, int[] indegree) {

        Queue<Integer> queue = new LinkedList<>();
        int count = 0; // ✅ Count kitne nodes process ho gaye

        // 🟢 Sab nodes jinke indegree 0 hai unko queue me daalo
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
                count++;
            }
        }

        // 🔁 BFS traversal
        while (!queue.isEmpty()) {
            int u = queue.poll(); // 🔻 Current node nikaalo

            // 🧭 Uske neighbours laao
            List<Integer> neighbors = adj.getOrDefault(u, new ArrayList<>());

            for (int v : neighbors) {
                indegree[v]--; // 🔧 Uska indegree kam karo
                if (indegree[v] == 0) {
                    queue.add(v); // 🔁 Agar ab 0 ho gaya to queue me daalo
                    count++;
                }
            }
        }

        // ✅ Agar saare nodes process ho gaye matlab koi cycle nahi hai
        return count == n;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // 📌 Step 1: Graph banao
        Map<Integer, List<Integer>> adj = new HashMap<>();

        // 🔢 Har node ka indegree rakho
        int[] indegree = new int[numCourses];

        // 🧱 Prerequisite pairs se graph banao
        for (int[] pair : prerequisites) {
            int a = pair[0]; // ✅ Course a ko lena hai
            int b = pair[1]; // ✅ Pehle course b lena hoga

            // b → a (edge from b to a)
            if (!adj.containsKey(b)) {
                adj.put(b, new ArrayList<>());
            }
            adj.get(b).add(a); // 👆 b ke baad a lena hoga

            indegree[a]++; // 🔢 a ka indegree badhao
        }

        // 🔁 Topological sort karke check karo cycle to nahi hai
        return topologicalSortCheck(adj, numCourses, indegree);
    }
}
