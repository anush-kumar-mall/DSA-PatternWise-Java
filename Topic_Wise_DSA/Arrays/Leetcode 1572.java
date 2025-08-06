// ✅ Problem: Matrix Diagonal Sum (LeetCode #1572)
// 🔗 Link: https://leetcode.com/problems/matrix-diagonal-sum/
//
// 🧠 Approach:
// - Loop through all rows from 0 to n-1.
// - Add both diagonals: mat[i][i] and mat[i][n - i - 1]
// - If n is odd, subtract the center element once (as it gets counted twice).
//
// 🧮 Time Complexity: O(n)
// 🧮 Space Complexity: O(1)

class Solution {
    public int diagonalSum(int[][] mat) {
        int sum = 0;
        int n = mat.length;

        for (int i = 0; i < n; i++) {
            sum += mat[i][i];               // Primary diagonal
            sum += mat[i][n - i - 1];       // Secondary diagonal
        }

        // If matrix size is odd, subtract the middle element once
        if (n % 2 == 1) {
            sum -= mat[n / 2][n / 2];
        }

        return sum;
    }
}
