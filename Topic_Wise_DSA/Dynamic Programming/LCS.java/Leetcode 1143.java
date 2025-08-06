import java.util.*;


// ✅ LeetCode 1143: Longest Common Subsequence
// 🔗 https://leetcode.com/problems/longest-common-subsequence/
// 🎯 Goal: Do strings ke beech longest common subsequence (LCS) ka length nikalna
//
// 💡 Approach: Top-Down DP (Memoization)
// - Har step pe agar characters match karte hain toh +1 lekar dono strings me ek-ek index peeche jaate hain
// - Agar match nahi karte toh max lete hain (ek string peeche jaake aur doosri ko same rakhke)
// - Memoization se overlapping subproblems avoid karte hain
//
// 🧠 DP + Recursion + Memoization
// 🧮 Time Complexity: O(m * n)
// 🧮 Space Complexity: O(m * n)



class Solution {

    // DP table jisme t[i][j] = LCS length from s1[0..i-1] and s2[0..j-1]
    
    private int[][] t;

    // Recursive function to calculate LCS length

    private int LCS(String s1, String s2, int m, int n) {

        // ✅ Base Case: Agar koi string khatam ho gayi

        if (m == 0 || n == 0) return 0;

        // Agar pehle se calculate kiya hua hai

        if (t[m][n] != -1) return t[m][n];

        // ✅ Characters match kar rahe hain

        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return t[m][n] = 1 + LCS(s1, s2, m - 1, n - 1);
        }

        // ❌ Characters match nahi kar rahe

        return t[m][n] = Math.max(LCS(s1, s2, m - 1, n), LCS(s1, s2, m, n - 1));
    }

    // Main function called by LeetCode

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // DP table ko initialize karo -1 se

        t = new int[m + 1][n + 1];
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }

        // Start recursion from full lengths of both strings

        return LCS(text1, text2, m, n);
    }
}
