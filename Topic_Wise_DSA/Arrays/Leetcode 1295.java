// ✅ Problem: Find Numbers with Even Number of Digits (LeetCode #1295)
// 🔗 Link: https://leetcode.com/problems/find-numbers-with-even-number-of-digits/
//
// 🧠 Approach:
// - Convert each number to a string using String.valueOf()
// - Check if the length (i.e., number of digits) is even
// - If yes, increase the count
//
// 🧮 Time Complexity: O(n * d), where n = nums.length, d = average number of digits per number
// 🧮 Space Complexity: O(1)

class Solution {
    public int findNumbers(int[] nums) {
        int count = 0;

        // Loop through each number in the array
        for (int i = 0; i < nums.length; i++) {
            // Convert number to string and check digit count
            if (String.valueOf(nums[i]).length() % 2 == 0) {
                count++; // Even digit count → increase result
            }
        }

        return count;
    }
}
