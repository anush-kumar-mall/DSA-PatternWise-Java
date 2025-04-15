

// ✅ Problem: Min Cost Climbing Stairs (LeetCode #746)
// 🔗 Link: https://leetcode.com/problems/min-cost-climbing-stairs/
//
// 🧠 Approach:
// - Har step pe ya toh 1 step ya 2 steps chadh sakte ho.
// - Har step ki ek cost hoti hai (array mein given hai).
// - Goal: Top tak pahuchne ki minimum total cost find karni hai.
// - Tum 0th ya 1st step se start kar sakte ho.
//
// ✅ Optimization:
// - Hum Bottom-Up DP (Tabulation) use kar rahe hain.
// - Ek DP array bana rahe hain jisme t[i] = ith step tak pahuchne ki minimum cost.
//
// 🧮 Time Complexity: O(n)  → har step ek baar hi calculate hota hai
// 🧮 Space Complexity: O(n) → DP array ke liye

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;

        // DP array: t[i] = ith step tak pahuchne ki minimum cost
        int[] t = new int[n];

        // Base cases
        t[0] = cost[0]; // 0th step tak pahuchne ki cost
        t[1] = cost[1]; // 1st step tak pahuchne ki cost

        // Bottom-Up DP: har step ka minimum cost calculate karo
        for (int i = 2; i < n; i++) {
            // Tum ya to 1 step peeche se aa sakte ho ya 2 steps peeche se
            // Dono me se jo minimum cost dega, usse current cost add kar do
            t[i] = cost[i] + Math.min(t[i - 1], t[i - 2]);
        }

        // Final result: Top pe pahuchne ke liye last ya second last step se jaoge
        // isliye dono me se minimum return karo
        return Math.min(t[n - 1], t[n - 2]);
    }
}
