// ✅ Problem: Find the Town Judge (LeetCode #997)
// 🔗 Link: https://leetcode.com/problems/find-the-town-judge/
//
// 🧠 Approach:
// - Each person is labeled from 1 to n.
// - The judge is trusted by everyone (n - 1 people) but trusts no one.
// - We use an array `count[]` to keep track:
//      - When person A trusts B → A loses a point, B gains a point.
// - Finally, the person with count = n - 1 is the judge.
//
// 🧮 Time Complexity: O(t + n), where t = trust.length
// 🧮 Space Complexity: O(n)

class Solution {
    public int findJudge(int n, int[][] trust) {
        int[] count = new int[n + 1]; // Index 0 unused

        for (int[] t : trust) {
            count[t[0]]--;  // Person who trusts someone
            count[t[1]]++;  // Person who is trusted
        }

        for (int i = 1; i <= n; i++) {
            if (count[i] == n - 1) {
                return i;
            }
        }

        return -1;
    }
}
