

// ✅ Problem: Climbing Stairs (LeetCode #70)
// 🔗 Link: https://leetcode.com/problems/climbing-stairs/
//
// 🧠 Approach:
// - Ye problem basically ek variation hai Fibonacci series ka.
// - Tum ek baar me 1 ya 2 steps chadh sakte ho.
// - Agar tum nth step par pahuch rahe ho, to tum ya to (n-1) se 1 step chadh ke aaye hoge,
//   ya (n-2) se 2 step chadh ke aaye hoge.
// - So, total ways = ways(n-1) + ways(n-2)
//
// ✅ Optimization:
// - Recursive solution TLE de sakta hai, isliye humne iterative DP approach use ki hai (Bottom-Up).
// - Hum sirf 3 variables ka use karke last 3 steps track karte hain, jisse space bhi optimize ho jaata hai.
//
// 🧮 Time Complexity: O(n)
// 🧮 Space Complexity: O(1)

class Solution {
    public int climbStairs(int n) {
        // Agar n 1, 2 ya 3 hai to direct return kar do
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }

        // a = ways to reach step 1
        int a = 1;

        // b = ways to reach step 2
        int b = 2;

        // c = ways to reach step 3
        int c = 3;

        // i=3 se leke n tak har step ke liye
        for (int i = 3; i < n; i++) {
            // Next step ke liye total ways = previous 2 steps ka sum
            c = a + b;

            // a, b ko update karte jao for next iteration
            int temp_b = b; // pehle b ko temp me store karo
            b = c;          // b ko c se update karo
            a = temp_b;     // a ko purane b se update karo
        }

        return c; // final answer = ways to reach nth step
    }
}
