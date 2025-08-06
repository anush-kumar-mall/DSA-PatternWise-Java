// ✅ Problem: Count Items Matching a Rule (LeetCode #1773)
// 🔗 Link: https://leetcode.com/problems/count-items-matching-a-rule/
//
// 🧠 Approach:
// - Each item is a list of [type, color, name]
// - Depending on ruleKey ("type", "color", "name"), choose correct index (0, 1, 2)
// - Count how many items match the ruleValue at that index
//
// 🧮 Time Complexity: O(n)
// 🧮 Space Complexity: O(1)

class Solution {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int ans = 0;
        int index = 0;

        // Decide index based on ruleKey
        if (ruleKey.equals("color")) {
            index = 1;
        } else if (ruleKey.equals("name")) {
            index = 2;
        }

        // Count items where value at index matches ruleValue
        for (List<String> item : items) {
            if (item.get(index).equals(ruleValue)) {
                ans++;
            }
        }

        return ans;
    }
}
