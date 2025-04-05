// ✅ Problem: Kids With the Greatest Number of Candies (LeetCode #1431)
// 🔗 Link: https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/
//
// 🧠 Approach:
// - Step 1: Find the maximum number of candies any kid currently has.
// - Step 2: For each kid, check if their candies + extraCandies >= maxCandies.
// - If yes, they *can* be the one with the greatest candies.
//
// 🧮 Time Complexity: O(n)
// 🧮 Space Complexity: O(n)

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxCandies = Integer.MIN_VALUE;

        // Step 1: Find the max number of candies any kid has
        for (int candy : candies) {
            maxCandies = Math.max(maxCandies, candy);
        }

        List<Boolean> result = new ArrayList<>();

        // Step 2: Check for each kid
        for (int candy : candies) {
            result.add(candy + extraCandies >= maxCandies);
        }

        return result;
    }
}
