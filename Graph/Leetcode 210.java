// ✅ Problem: Course Schedule II (LeetCode #210)
// 🔗 https://leetcode.com/problems/course-schedule-ii
//
// 🧠 Goal: Saare courses ka ek valid order return karo jisme sab courses complete ho sakein
// Agar koi valid order nahi hai (cycle hai) to return empty array []

// 🧠 Approach: Topological Sort using Kahn’s Algorithm (BFS)
// ⏱️ Time: O(V + E), 📦 Space: O(V + E)

import java.util.*;

class Solution {

    // 📌 Ye method topological sort karta hai aur order return karta hai
    public List<Integer> topologicalSortCheck(Map<Integer, List<Integer>> adj, int n, int[] indegree) {
        Queue<Integer> que = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        int count = 0;

        // 🟢 Step 1: Sab nodes jinka indegree 0 hai unko queue me daal do
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                que.offer(i);       // Queue me daal do
                result.add(i);      // Ye course pehle ho sakta hai
                count++;            // Ek aur course complete ho gaya
            }
        }

        // 🔁 Step 2: BFS traversal (Kahn’s Algo)
        while (!que.isEmpty()) {
            int u = que.poll();    // Current course

            // 🧭 Uske neighbors (dependent courses) dekho
            if (adj.containsKey(u)) {
                for (int v : adj.get(u)) {
                    indegree[v]--;     // 🔧 Uska indegree kam karo

                    if (indegree[v] == 0) {
                        que.offer(v);      // ✅ Ab ready hai queue me daalne ke liye
                        result.add(v);     // Order me daal do
                        count++;           
                    }
                }
            }
        }

        // 🚫 Agar cycle mili, ya kuch course bacha jiska indegree kabhi 0 nahi hua
        if (count != n) return new ArrayList<>();

        return result; // ✅ Valid topological order
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adj = new HashMap<>();    // 📘 Graph banane ke liye
        int[] indegree = new int[numCourses];                // 🔢 Har course ka indegree

        // 📌 Step 1: Graph and indegree array banao
        for (int[] edge : prerequisites) {
            int a = edge[0]; // a course lena hai
            int b = edge[1]; // b pehle lena hoga
            adj.computeIfAbsent(b, k -> new ArrayList<>()).add(a); // b → a
            indegree[a]++; // a ka indegree badhao
        }

        // 🧠 Step 2: Topological sort se order lelo
        List<Integer> topoOrder = topologicalSortCheck(adj, numCourses, indegree);

        // 🧪 Step 3: Result ko array me convert karo (as per return type)
        int[] result = new int[topoOrder.size()];
        for (int i = 0; i < topoOrder.size(); i++) {
            result[i] = topoOrder.get(i);
        }

        return result;
    }
}
