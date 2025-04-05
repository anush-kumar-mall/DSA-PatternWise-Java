// ✅ Problem: Transpose Matrix (LeetCode #867)
// 🔗 Link: https://leetcode.com/problems/transpose-matrix/
//
// 🧠 Approach:
// - Transpose of a matrix means converting all rows to columns and columns to rows.
// - For a matrix of size m x n, the transposed matrix will be of size n x m.
// - We create a new matrix of size [n][m] and fill it by swapping indices: ans[i][j] = matrix[j][i]
//
// 🧮 Time Complexity: O(m * n)
// 🧮 Space Complexity: O(m * n) — new matrix used for storing result
//
// 📌 Status: Accepted on LeetCode ✅

class Solution {
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;         // number of rows
        int n = matrix[0].length;      // number of columns

        int[][] ans = new int[n][m];   // transposed matrix will have size n x m

        // Swap rows with columns
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans[i][j] = matrix[j][i];
            }
        }

        return ans;
    }
}
