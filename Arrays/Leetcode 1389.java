// ✅ Problem: Create Target Array in the Given Order (LeetCode #1389)
// 🔗 Link: https://leetcode.com/problems/create-target-array-in-the-given-order/
//
// 🧠 Approach:
// - Use an ArrayList to dynamically insert elements at the given index.
// - For each i, insert nums[i] at position index[i].
// - After insertion, convert the list to a regular int[] array.
//
// 🧮 Time Complexity: O(n^2) in worst-case due to insertions in ArrayList
// 🧮 Space Complexity: O(n)

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> target = new ArrayList<>();

        // Insert each nums[i] at position index[i]
        for (int i = 0; i < nums.length; i++) {
            target.add(index[i], nums[i]);
        }

        // Convert the list to an array
        int[] result = new int[target.size()];
        for (int i = 0; i < target.size(); i++) {
            result[i] = target.get(i);
        }

        return result;
    }
}
