// ✅ Problem: Concatenation of Array (LeetCode #1929)
// 🔗 Link: https://leetcode.com/problems/concatenation-of-array/
//
// 🧠 Approach:
// - Create a new array of size 2 * n
// - Copy nums[i] to ans[i] and also to ans[i + n]
// - This way we copy nums twice in a single loop
//
// 🧮 Time Complexity: O(n)
// 🧮 Space Complexity: O(n)

class Solution {
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2 * n];  // New array of double size

        for (int i = 0; i < n; i++) {
            ans[i] = nums[i];        // First copy
            ans[i + n] = nums[i];    // Second copy
        }

        return ans;
    }
}
