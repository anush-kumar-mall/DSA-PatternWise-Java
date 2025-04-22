import java.util.*;





// ✅ LeetCode 518: Coin Change II
// 🔗 https://leetcode.com/problems/coin-change-ii/
// 🎯 Goal: Ek amount banane ke kitne distinct ways hai given coins ka unlimited use karke.
//
// 💡 Approach: Top-Down DP with Memoization
// - Recursive way se try karte hain coins lena ya skip karna
// - Memoization use karke repeated calls ko avoid karte hain
//
// 🧠 DP + Recursion + Memo
// 🧮 Time Complexity: O(n * amount)  [n = number of coins]
// 🧮 Space Complexity: O(n * amount)  [memo table]

class Solution {

    // DP table: memo[i][amt] = i-th index se lekar 'amt' banane ke ways
    int[][] memo;
    int n; // number of coins

    // Recursive function: har step pe ya to coin lo ya skip karo
    public int numberOfWays(int[] coins, int i, int amount) {

        // ✅ Base Case: Agar amount 0 ban gaya, ek valid combination mil gaya
        if (amount == 0) return 1;

        // ❌ Base Case: Agar coins khatam ho gaye ya amount negative ho gaya
        if (i == n || amount < 0) return 0;

        // Memoization check: agar already calculated hai
        if (memo[i][amount] != -1) return memo[i][amount];

        // 💡 Optimization: Agar current coin amount se bada hai to use mat lo
        if (coins[i] > amount) {
            return memo[i][amount] = numberOfWays(coins, i + 1, amount); // skip
        }

        // ✅ Option 1: Coin lo aur amount me se minus karo (i same rahega kyunki coin unlimited hai)
        int take = numberOfWays(coins, i, amount - coins[i]);

        // ❌ Option 2: Coin skip karo aur next coin pe jao
        int skip = numberOfWays(coins, i + 1, amount);

        // Total combinations = take + skip
        return memo[i][amount] = take + skip;
    }

    public int change(int amount, int[] coins) {
        n = coins.length;  // Number of coins
        memo = new int[n][amount + 1];  // Memo table bana lo

        // Har cell ko -1 se initialize karo (means not solved yet)
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Start recursion from 0th index and full amount
        return numberOfWays(coins, 0, amount);
    }
}
