



// ✅ LeetCode 322: Coin Change
// 🔗 https://leetcode.com/problems/coin-change/
// 🎯 Goal: Minimum number of coins chahiye ek given amount ko banane ke liye.
//
// 💡 Approach:
// - Bottom-Up DP (Tabulation)
// - Har amount ke liye check karo kaunsa coin use karke minimum coins lagte hain
//
// 🧠 DP Tabulation
// 🧮 Time Complexity: O(amount * number of coins)
// 🧮 Space Complexity: O(amount)

class Solution {
    public int coinChange(int[] coins, int amount) {

        // Agar amount hi 0 ya negative hai to coins ki zarurat hi nahi
        if (amount < 1) return 0;

        // DP array: minCoinsDP[i] = i banane ke liye minimum coins
        int[] minCoinsDP = new int[amount + 1];

        // Step 1: DP array ko initialize karo
        for (int i = 1; i <= amount; i++) {
            minCoinsDP[i] = Integer.MAX_VALUE;

            // Step 2: Har coin try karo
            for (int coin : coins) {

                // Agar coin <= i hai aur pehle wala amount reachable hai
                if (coin <= i && minCoinsDP[i - coin] != Integer.MAX_VALUE) {
                    // Minimum coins ka update karo
                    minCoinsDP[i] = Math.min(minCoinsDP[i], 1 + minCoinsDP[i - coin]);
                }
            }
        }

        // Agar amount ban hi nahi raha to -1 return karo
        return minCoinsDP[amount] == Integer.MAX_VALUE ? -1 : minCoinsDP[amount];
    }
}
