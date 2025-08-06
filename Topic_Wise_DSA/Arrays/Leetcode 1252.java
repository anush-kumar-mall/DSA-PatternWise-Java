// ✅ Problem: Cells with Odd Values in a Matrix (LeetCode #1252)
// 🔗 Link: https://leetcode.com/problems/cells-with-odd-values-in-a-matrix/
//
// 🧠 Approach:
// - Create 2 boolean arrays to track toggled rows and columns.
// - For each index [i, j], toggle row[i] and col[j].
// - A cell ends up with an odd value if it was toggled an odd number of times:
//      - Either its row or its column (but not both) is toggled.
// - Count how many rows and columns were toggled (true).
// - Use formula: 
//      Total odd = (r * n) + (c * m) - (2 * r * c)
//      where r = toggled rows, c = toggled columns
//
// 🧮 Time Complexity: O(k + m + n), where k = indices.length
// 🧮 Space Complexity: O(m + n)

class Solution {
    public int oddCells(int m, int n, int[][] indices) {
        boolean[] row = new boolean[m]; // Track toggled rows
        boolean[] col = new boolean[n]; // Track toggled columns

        // Toggle rows and columns based on indices
        for (int i = 0; i < indices.length; i++) {
            row[indices[i][0]] ^= true; // Flip row
            col[indices[i][1]] ^= true; // Flip column
        }

        int r = 0, c = 0;

        // Count number of toggled rows
        for (int i = 0; i < m; i++) {
            if (row[i]) r++;
        }

        // Count number of toggled columns
        for (int i = 0; i < n; i++) {
            if (col[i]) c++;
        }

        // Use formula to calculate total odd cells
        return r * n + c * m - 2 * r * c;
    }
}
