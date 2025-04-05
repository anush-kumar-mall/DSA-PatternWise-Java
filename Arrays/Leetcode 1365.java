// ✅ Problem: How Many Numbers Are Smaller Than the Current Number (LeetCode #1365)
// 🔗 Link: https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
//
// 🧠 Approach:
// - Since nums[i] is in range 0 to 100, we use a frequency array (temp[]).
// - Step 1: Count frequency of each number in temp[].
// - Step 2: Convert it into prefix sum → temp[i] = count of numbers <= i.
// - Step 3: For each nums[i], answer is temp[nums[i] - 1] (i.e., how many numbers are less than it).
//
// 🧮 Time Complexity: O(n + k), where k = 101 (constant range of numbers)
// 🧮 Space Complexity: O(k) = O(101)

class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] res = new int[nums.length];
        int[] temp = new int[101]; // Frequency array for numbers from 0 to 100

        // Count frequency of each number in nums
        for (int i = 0; i < nums.length; i++) {
            temp[nums[i]]++;
        }

        // Convert to prefix sum array
        for (int i = 1; i < 101; i++) {
            temp[i] += temp[i - 1];
        }

        // Fill result using prefix array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                res[i] = 0; // No number is smaller than 0
            } else {
                res[i] = temp[nums[i] - 1];
            }
        }

        return res;
    }
}
