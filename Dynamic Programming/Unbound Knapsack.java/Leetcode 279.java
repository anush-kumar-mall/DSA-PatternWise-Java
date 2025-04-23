import java.util.*;



// ✅ LeetCode 279: Perfect Squares
// 🔗 https://leetcode.com/problems/perfect-squares/
// 🎯 Goal: Minimum number of perfect square numbers needed to sum up to n
//
// 💡 Approach: Top-Down DP with Memoization
// - Har step pe hum possible perfect squares try karte hain (1^2, 2^2, 3^2...)
// - Fir recursion se baaki remaining n ke liye minimum squares nikalte hain
//
// 🧠 DP + Recursion + Memo
// 🧮 Time Complexity: O(n * sqrt(n))
// 🧮 Space Complexity: O(n)



 class Solution {

    // DP table jisme t[i] = minimum squares to form sum 'i'
    private int[] t = new int[10001];

    // Recursive function to calculate minimum squares for a number 'n'
    private int minSquares(int n) {
        // ✅ Base case: Agar n 0 ho gaya, toh koi square nahi chahiye
        if (n == 0) return 0;

        // Memoization: Agar pehle se calculate kiya hai
        if (t[n] != -1) return t[n];

        int minCount = Integer.MAX_VALUE;

        // Har perfect square i*i try karo jab tak i*i <= n
        for (int i = 1; i * i <= n; i++) {
            // Choose i^2 and recursively solve for remaining (n - i^2)
            minCount = Math.min(minCount, 1 + minSquares(n - i * i));
        }

        // DP table me result store kar do
        return t[n] = minCount;
    }

    // Main function called by LeetCode
    public int numSquares(int n) {
        // DP array ko initialize karo -1 se (not solved yet)
        Arrays.fill(t, -1);

        // Start solving from number n
        return minSquares(n);
    }
}
