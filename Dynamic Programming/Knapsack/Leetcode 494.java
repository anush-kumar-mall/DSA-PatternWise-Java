// ✅ LeetCode 494: Target Sum
// 🔗 https://leetcode.com/problems/target-sum/
// 🎯 Goal: Array ke elements ko + ya - karke target sum banana hai
//
// 💡 Approach:
// - Har number ke liye do options hain: usse + ya - karna
// - Har step pe sum change hota hai, aur hum recursion se har possibility explore karte hain
// - Memoization se repeated subproblems ko avoid kiya hai
//
// 🧠 DP + Recursion + Memoization
// 🧮 Time Complexity: O(n * total sum)
// 🧮 Space Complexity: O(n * total sum) because of memoization

import java.util.*;

class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        // Memoization ke liye ek map banaya: key = i,sum | value = number of ways
        HashMap<String, Integer> memo = new HashMap<>();
        // Recursive function ko start karo index 0 aur sum 0 se
        return solve(nums, target, 0, 0, memo);
    }

    private int solve(int[] nums, int target, int i, int sum, HashMap<String, Integer> memo) {
        // Base case: agar poore array ka traverse ho gaya
        if (i == nums.length) {
            // Agar current sum target ke barabar hai to 1 way mila
            return sum == target ? 1 : 0;
        }

        // Har unique state ke liye ek key banayi: "i,sum"
        String key = i + "," + sum;

        // Agar pehle se result available hai to directly return karo
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Do options: +nums[i] ya -nums[i]
        int plus = solve(nums, target, i + 1, sum + nums[i], memo);
        int minus = solve(nums, target, i + 1, sum - nums[i], memo);

        // Total ways store karo memo me
        memo.put(key, plus + minus);

        // Return total ways for current state
        return memo.get(key);
    }
}
