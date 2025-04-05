// ✅ Problem: Number of Good Pairs (LeetCode #1512)
// 🔗 Link: https://leetcode.com/problems/number-of-good-pairs/
//
// 🧠 Approach:
// - Use a frequency array `temp[]` to track how many times each number has appeared so far.
// - For every `nums[i]`, add temp[nums[i]] to result, because that’s how many times this number has already occurred before.
// - Increment the count of nums[i] in temp.
//
// 🔁 This gives count of all (i, j) pairs such that i < j and nums[i] == nums[j].
//
// 🧮 Time Complexity: O(n)
// 🧮 Space Complexity: O(1) — since max number is ≤ 100

class Solution {
    public int numIdenticalPairs(int[] nums) {
        int[] temp = new int[101]; // Frequency array for values from 0 to 100
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            result += temp[nums[i]]; // Count how many times this number has occurred before
            temp[nums[i]]++;         // Increment count of current number
        }

        return result;
    }
}
