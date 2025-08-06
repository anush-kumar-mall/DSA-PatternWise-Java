// ✅ Problem: Running Sum of 1d Array (LeetCode #1480)
// 🔗 Link: https://leetcode.com/problems/running-sum-of-1d-array/
//
// 🧠 Approach:
// - Traverse from index 1 to end.
// - For each i, add the previous value to current → nums[i] = nums[i] + nums[i - 1]
// - This gives cumulative sum up to i.
//
// 🔁 In-place update: No extra array is used.
// 🧮 Time Complexity: O(n)
// 🧮 Space Complexity: O(1)

class Solution {
    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1]; // Add previous sum to current element
        }
        return nums;
    }
}
