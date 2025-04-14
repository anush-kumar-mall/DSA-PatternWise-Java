import java.util.*;


// ✅ Problem: Fibonacci Number (LeetCode #509)
// 🔗 Link: https://leetcode.com/problems/fibonacci-number/
//
// 🧠 Approach:
// - Fibonacci series: 0, 1, 1, 2, 3, 5, 8, ...
// - Formula: F(n) = F(n-1) + F(n-2)
// - Base Cases: F(0) = 0, F(1) = 1
//
// ✅ Optimization:
// - Recursive solution TLE deta hai for large n.
// - Isliye yahan humne Bottom-Up Dynamic Programming use kiya hai (Tabulation).
// - Hum ek array banate hain jisme har index par uss index ka Fibonacci number store hota hai.
//
// 🧮 Time Complexity: O(n)
// 🧮 Space Complexity: O(n) → DP array ke liye

class Solution {
    public int fib(int n) {
        // Base case: Agar n = 0 ya 1 hai to wahi return karo
        if (n == 0 || n == 1) {
            return n;
        }

        // DP array: t[i] = i-th Fibonacci number
        int[] t = new int[n + 1];

        t[0] = 0; // F(0)
        t[1] = 1; // F(1)

        // Bottom-Up approach: har index ka fib calculate karo
        for (int i = 2; i <= n; i++) {
            t[i] = t[i - 1] + t[i - 2];
        }

        // Final result: F(n)
        return t[n];
    }
}
