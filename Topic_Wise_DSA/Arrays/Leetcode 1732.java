// ✅ Problem: Find the Highest Altitude (LeetCode #1732)
// 🔗 Link: https://leetcode.com/problems/find-the-highest-altitude/
//
// 🧠 Approach:
// - Start with initial altitude = 0
// - Traverse the gain array, add each value to current altitude.
// - Track the maximum altitude reached.
//
// 🧮 Time Complexity: O(n)
// 🧮 Space Complexity: O(1)

class Solution {
    public int largestAltitude(int[] gain) {
        int max = 0;        // Maximum altitude reached so far
        int current = 0;    // Current altitude

        for (int i = 0; i < gain.length; i++) {
            current += gain[i];                 // Update current altitude
            max = Math.max(current, max);       // Update max if needed
        }

        return max;
    }
}
