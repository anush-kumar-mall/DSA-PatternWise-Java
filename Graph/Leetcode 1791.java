// ✅ Problem: Find Center of Star Graph (LeetCode #1791)
// 🔗 Link: https://leetcode.com/problems/find-center-of-star-graph/
//
// 🧠 Approach:
// - Star graph me ek central node hoti hai jo har edge me connected hoti hai.
// - Sirf pehli do edges check karke common node nikal lo — wahi center hoga.
//
// 🧮 Time Complexity: O(1) → Sirf 2 edges check kar rahe hain
// 🧮 Space Complexity: O(1)

class Solution {
    public int findCenter(int[][] edges) {
        int[] first = edges[0];  // 🔹 First edge → [a, b]
        int[] second = edges[1]; // 🔹 Second edge → [c, d]

        // 🔍 Check karo kaunsi node dono edges me common hai — wahi center node hai
        if (first[0] == second[0] || first[0] == second[1]) {
            return first[0]; // ✅ Common node found
        }

        return first[1]; // ✅ Agar first[0] match nahi hua, toh first[1] hi center hoga
    }
}
