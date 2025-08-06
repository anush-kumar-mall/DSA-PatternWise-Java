// ✅ Problem: Build Array from Permutation (LeetCode #1920)
// 🔗 Link: https://leetcode.com/problems/build-array-from-permutation/
//
// 🧠 Approach:
// - Create a new array 'ans' of same length.
// - For each index i, set ans[i] = nums[nums[i]] as per problem.
// - Return the result.
//
// 🧮 Time Complexity: O(n)
// 🧮 Space Complexity: O(n)

class Solution {
    public int[] buildArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        // Traverse each index and build the answer array
        for (int i = 0; i < n; i++) {
            ans[i] = nums[nums[i]];
        }

        return ans;
    }
}
