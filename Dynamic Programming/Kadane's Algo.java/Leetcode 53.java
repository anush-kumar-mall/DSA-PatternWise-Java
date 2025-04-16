// ✅ Problem: Maximum Subarray (LeetCode #53)
// 🔗 Link: https://leetcode.com/problems/maximum-subarray/
//
// 📌 Approach: Kadane’s Algorithm
// - Har element pe ye sochna hai: kya naya subarray start karna better hai ya pehle waale ko continue karna.
// - Isliye hum current sum ko max(nums[i], currentSum + nums[i]) se update karte hain.
// - Saath hi, har step pe maxSum track karte hain jo ab tak ka highest sum hai.
//
// 🧠 Kadane’s works because it greedily chooses the best subarray ending at every index.
//
// 🧮 Time Complexity: O(n)
// 🧮 Space Complexity: O(1)

class Solution {
    public int maxSubArray(int[] nums) {
        // Step 1: current subarray sum ko nums[0] se start karo
        int currentSum = nums[0];

        // Step 2: max subarray sum ko bhi nums[0] se hi initialize karo
        int maxSum = nums[0];

        // Step 3: Index 1 se end tak jao
        for (int i = 1; i < nums.length; i++) {

            // Decide: naya subarray start karo ya pehle waala continue karo
            currentSum = Math.max(nums[i], currentSum + nums[i]);

            // Max sum update karo agar currentSum bada hai
            maxSum = Math.max(maxSum, currentSum);
        }

        // Final result return
        return maxSum;
    }
}
